package com.wtcrmandroid.model;

/**
 * Created by ${Lee} on 2017/6/28
 */

public class ContactsChild {
    private String url;
    private String name;
    private String department;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public ContactsChild(String url, String name, String department) {
        this.url = url;
        this.name = name;
        this.department = department;
    }
}
