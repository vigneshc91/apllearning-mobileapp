package com.learning.apl.apllearning;

/**
 * Created by shiva on 6/11/16.
 */

public class SubjectModel {

    private String subjectId;
    private String grade;
    private String name;
    private String createdAt;
    private String updatedAt;

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
