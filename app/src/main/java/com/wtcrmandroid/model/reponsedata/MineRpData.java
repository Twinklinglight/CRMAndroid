package com.wtcrmandroid.model.reponsedata;

/**
 * Created by zxd on 2017/7/25.
 */

public class MineRpData {

    /**
     * attribution : WT
     * username:wt
     * departmentId : 26
     * departmentName : 物通电销二部
     * departments : 6,25,26,27,32,33,34,35
     * teamId : 0
     * teamName :
     * headerImg:
     * teams :
     * groupId : 1
     * groupName : 公司总部
     * roleId : 24
     * roleName : 客户经理(物通)
     * telPhone : 4000105656
     */

    private String attribution;
    private int departmentId;
    private String departmentName;
    private String departments;
    private int teamId;
    private String teamName;
    private String teams;
    private int groupId;
    private String groupName;
    private int roleId;
    private String roleName;
    private String telPhone;
    private String username;
    private String headerImg;

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    @Override
    public String toString() {
        return "MineRpData{" +
                "attribution='" + attribution + '\'' +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", departments='" + departments + '\'' +
                ", teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teams='" + teams + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", telPhone='" + telPhone + '\'' +
                ", username='" + username + '\'' +
                ", headerImg='" + headerImg + '\'' +
                '}';
    }
}
