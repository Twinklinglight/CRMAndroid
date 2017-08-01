package com.wtcrmandroid.model.reponsedata;

import java.util.List;

/**
 * Created by zxd on 2017/7/29.
 */

public class ContactsDpmentRP {

    private int DepartmentID;
    private String DepartmentName;
    private List<ContactRP>Users;

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int departmentID) {
        DepartmentID = departmentID;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public List<ContactRP> getUsers() {
        return Users;
    }

    public void setUsers(List<ContactRP> users) {
        Users = users;
    }
}
