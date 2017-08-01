package com.wtcrmandroid.model.reponsedata;

/**
 * Created by wt-pc on 2017/8/1.
 * 检查版本信息
 */

public class CheckVersionRP {

    /**
     * ForceUpdate : NO
     * VersionPath : crm.chinawutong.com
     * VersionCode : 1.0.0.0
     */

    private String ForceUpdate;
    private String VersionPath;
    private String VersionCode;

    public String getForceUpdate() {
        return ForceUpdate;
    }

    public void setForceUpdate(String ForceUpdate) {
        this.ForceUpdate = ForceUpdate;
    }

    public String getVersionPath() {
        return VersionPath;
    }

    public void setVersionPath(String VersionPath) {
        this.VersionPath = VersionPath;
    }

    public String getVersionCode() {
        return VersionCode;
    }

    public void setVersionCode(String VersionCode) {
        this.VersionCode = VersionCode;
    }
}
