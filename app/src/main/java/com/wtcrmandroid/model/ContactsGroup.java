package com.wtcrmandroid.model;

import java.util.List;

/**
 * ContactsGroup
 *
 * @author lenovo  2017/5/4.
 *         Function Describe
 * @modify lenovo  2017/5/4.
 * Function Describe
 */
public class ContactsGroup {
    private String title;
    private List<ContactsChild> childList;

    public ContactsGroup(String title, List<ContactsChild> childList) {
        this.title = title;
        this.childList = childList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ContactsChild> getChildList() {
        return childList;
    }

    public void setChildList(List<ContactsChild> childList) {
        this.childList = childList;
    }
}
