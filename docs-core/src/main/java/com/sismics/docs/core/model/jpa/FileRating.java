package com.sismics.docs.core.model.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

/**
 * Rating entity.
 */
@Entity
@Table(name = "T_FIL_RATING")
public class FileRating implements Loggable {
    /**
     * Rating ID.
     */
    @Id
    @Column(name = "RAT_ID_C", length = 36)
    private String id;
    
    /**
     * File ID.
     */
    @Column(name = "RAT_IDFIL_C", length = 36, nullable = false)
    private String fileId;
    
    /**
     * User ID.
     */
    @Column(name = "RAT_IDUSER_C", length = 36, nullable = false)
    private String userId;

    /**
     * Academics rating.
     * Integer (Java) -> decimal (SQL) 
     */
    @Column(name = "RAT_ACADEMICS_C", nullable = false)
    private Integer academics;
    
    /**
     * Activities rating.
     */
    @Column(name = "RAT_ACTIVITIES_C", nullable = false)
    private Integer activities;

    /**
     * Experience rating.
     */
    @Column(name = "RAT_EXPERIENCE_C", nullable = false)
    private Integer experience;

    /**
     * Awards rating.
     */
    @Column(name = "RAT_AWARDS_C", nullable = false)
    private Integer awards;

    /**
     * Overall rating.
     */
    @Column(name = "RAT_OVERALL_C", nullable = false)
    private Integer overall;

    /**
     * Comment.
     */
    @Column(name = "RAT_COMMENT_C", nullable = false)
    private String comment;
    
    /**
     * Creation date.
     */
    @Column(name = "RAT_CREATEDATE_D", nullable = false)
    private Date createDate;

    /**
     * Deletion date.
     */
    @Column(name = "RAT_DELETEDATE_D")
    private Date deleteDate;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }
    
    public Integer getAcademics() {
        return academics;
    }

    public void setAcademics(Integer academics) {
        this.academics = academics;
    }

    public Integer getActivities() {
        return activities;
    }

    public void setActivities(Integer activities) {
        this.activities = activities;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getAwards() {
        return awards;
    }

    public void setAwards(Integer awards) {
        this.awards = awards;
    }

    public Integer getOverall() {
        return overall;
    }

    public void setOverall(Integer overall) {
        this.overall = overall;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("fileId", fileId)
                .add("userId", userId)
                .toString();
    }

    @Override
    public String toMessage() {
        return fileId;
    }
}
