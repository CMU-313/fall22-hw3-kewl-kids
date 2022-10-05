package com.sismics.docs.core.dao;

import com.sismics.docs.core.model.jpa.FileRating;
import com.sismics.docs.core.constant.AuditLogType;
import com.sismics.docs.core.dao.dto.FileRatingDto;
import com.sismics.docs.core.util.AuditLogUtil;
import com.sismics.util.context.ThreadLocalContext;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * File Rating DAO.
 */
public class FileRatingDao {
        /**
        * Creates a new rating.
        * 
        * @param rating Rating
        * @param userId User ID
        * @return New ID
        */
    public String create(FileRating rating, String userId) {
        // Create the UUID
        rating.setId(UUID.randomUUID().toString());
        
        // Create the rating
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        rating.setCreateDate(new Date());
        em.persist(rating);
        
        // Create audit log
        AuditLogUtil.create(rating, AuditLogType.CREATE, userId);
        
        return rating.getId();
    }
    
    /**
     * Deletes a rating.
     * 
     * @param id Rating ID
     * @param userId User ID
     */
    public void delete(String id, String userId) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
            
        // Get the rating
        Query q = em.createQuery("select c from FileRating c where c.id = :id and c.deleteDate is null");
        q.setParameter("id", id);
        FileRating ratingDb = (FileRating) q.getSingleResult();
        
        // Delete the rating
        Date dateNow = new Date();
        ratingDb.setDeleteDate(dateNow);

        // Create audit log
        AuditLogUtil.create(ratingDb, AuditLogType.DELETE, userId);
    }
    
    /**
     * Gets an active rating by its ID.
     * 
     * @param id Rating ID
     * @return Rating
     */
    public FileRating getActiveById(String id) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        try {
            Query q = em.createQuery("select r from FileRating r where r.id = :id and r.deleteDate is null");
            q.setParameter("id", id);
            return (FileRating) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    /**
     * Get all ratingDtos on a file.
     * 
     * @param fileId File ID
     * @return List of ratings
     */
    public List<FileRatingDto> getDtoByFileId(String fileId) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        StringBuilder sb = new StringBuilder(
                "select r.RAT_ID_C, r.RAT_ACADEMICS_C, r.RAT_ACTIVITIES_C, r.RAT_EXPERIENCE_C, r.RAT_AWARDS_C,  r.RAT_OVERALL_C, r.RAT_COMMENT_C, r.RAT_CREATEDATE_D, u.USE_USERNAME_C from T_FILE_RATING r, T_USER u");
        sb.append(" where r.RAT_IDFIL_C = :fileId and r.RAT_IDUSER_C = u.USE_ID_C and r.RAT_DELETEDATE_D is null ");
        sb.append(" order by r.RAT_CREATEDATE_D asc ");
        Query q = em.createNativeQuery(sb.toString());
        q.setParameter("fileId", fileId);
        @SuppressWarnings("unchecked")
        List<Object[]> l = q.getResultList();

        List<FileRatingDto> ratingDtoList = new ArrayList<>();
        for (Object[] o : l) {
            int i = 0;
            FileRatingDto ratingDto = new FileRatingDto();
            ratingDto.setId((String) o[i++]);
            ratingDto.setAcademics((Integer) o[i++]);
            ratingDto.setActivities((Integer) o[i++]);
            ratingDto.setExperience((Integer) o[i++]);
            ratingDto.setAwards((Integer) o[i++]);
            ratingDto.setOverall((Integer) o[i++]);
            ratingDto.setComment((String) o[i++]);
            ratingDto.setCreateTimestamp(((Timestamp) o[i++]).getTime());
            ratingDto.setCreatorName((String) o[i++]);
            ratingDtoList.add(ratingDto);
        }
        return ratingDtoList;
    }
    
    /**
     * Get all ratings on a file.
     * 
     * @param fileId File ID
     * @return List of ratings
     */
    public List<FileRating> getByFileId(String fileId) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        TypedQuery<FileRating> q = em.createQuery(
                "select r from FileRating r where r.fileId = :fileId and r.deleteDate is null order by r.createDate asc ",
                FileRating.class);
        q.setParameter("fileId", fileId);
        return q.getResultList();
    }
}
