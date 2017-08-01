package com.wtcrmandroid.model.requestdata;

/**
 * Created by zxd on 2017/7/26.
 */

public class UpdatePhotoRQ {

    private int userId;
    private CustomerCallRQ.ImageFile img;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public CustomerCallRQ.ImageFile getImg() {
        return img;
    }

    public void setImg(CustomerCallRQ.ImageFile img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "UpdatePhotoRQ{" +
                "userId=" + userId +
                ", img=" + img +
                '}';
    }
}
