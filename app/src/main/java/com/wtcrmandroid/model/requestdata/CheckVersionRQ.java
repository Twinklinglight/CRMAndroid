package com.wtcrmandroid.model.requestdata;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wt-pc on 2017/8/1.
 * 检查版本信息
 */

public class CheckVersionRQ implements Serializable {

    @SerializedName("VersionName")
    private String VersionName;

    public String getVersionName() {
        return VersionName;
    }

    public void setVersionName(String versionName) {
        VersionName = versionName;
    }
}
