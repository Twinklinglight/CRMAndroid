package com.wtcrmandroid.model.data;

/**
 * Created by wt-pc on 2017/7/19.
 */

public class HomeItemD {
    private String name;
    private int url;
    private int click;

    public HomeItemD(int url, String name, int click) {
        this.url = url;
        this.name = name;
        this.click = click;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }
}
