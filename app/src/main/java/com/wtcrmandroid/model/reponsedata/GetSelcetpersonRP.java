package com.wtcrmandroid.model.reponsedata;

/**
 * Created by zxd on 2017/7/13.
 */

public class GetSelcetpersonRP {

    private String name;
    private String job;
    private String roleLevel;

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
