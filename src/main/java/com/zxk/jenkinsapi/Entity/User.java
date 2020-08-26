package com.zxk.jenkinsapi.Entity;

public class User {
     String userNames;
     String projectNames;
     String repoUrls;
     String createProDates;
     String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    public String getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String projectNames) {
        this.projectNames = projectNames;
    }

    public String getRepoUrls() {
        return repoUrls;
    }

    public void setRepoUrls(String repoUrls) {
        this.repoUrls = repoUrls;
    }

    public String getCreateProDates() {
        return createProDates;
    }

    public void setCreateProDates(String createProDates) {
        this.createProDates = createProDates;
    }



}
