package com.sismics.docs.core.dao.dto;

/**
 * File Rating DTO.
 */
public class FileRatingDto {
    /**
     * Rating ID.
     */
    private String id;
    
    /**
     * Creator name.
     */
    private String creatorName;
    
    /**
     * Academics rating.
     */
    private Integer academics;

    /**
     * Activities rating.
     */
    private Integer activities;

    /**
     * Experience rating.
     */
    private Integer experience;

    /**
     * Awards rating.
     */
    private Integer awards;

    /**
     * Overall rating.
     */
    private Integer overall;

    /**
     * Rating comment.
     */
    private String comment;
    
    /**
     * Creation date of this rating.
     */
    private Long createTimestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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

    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
