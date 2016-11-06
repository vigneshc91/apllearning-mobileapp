package com.learning.apl.apllearning;

/**
 * Created by shiva on 6/11/16.
 */

public class MaterialModel {

    private String materialId;
    private String userId;
    private String gradeId;
    private String subjectId;
    private String title;
    private String url;
    private String description;
    private String createdAt;
    private String updatedAt;

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
