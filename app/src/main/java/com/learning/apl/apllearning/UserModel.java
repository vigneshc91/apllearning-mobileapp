package com.learning.apl.apllearning;

/**
 * Created by shiva on 6/11/16.
 */

public class UserModel {

    private String userId;
    private String userName;
    private String userType;
    private String gradeId;
    private String grade;
    private String section;
    private String createdAt;
    private String updatedAt;

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return this.userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSection() {
        return section;
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
