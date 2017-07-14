package com.wtcrmandroid.model.requestdata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wt-pc on 2017/7/14.
 */

public class CustomerCallRQ {
    private String userId;
    private String customerId;
    private String customerName ;
    private Double lat;
    private Double lng;
    private String addressDetail ;
    private String remarks;
    private List<ImageFile> img=new ArrayList<>() ;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<ImageFile> getImg() {
        return img;
    }

    public void setImg(List<ImageFile> img) {
        this.img = img;
    }

   public static class ImageFile{
        private String filename;
        private String basecode;

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getBasecode() {
            return basecode;
        }

        public void setBasecode(String basecode) {
            this.basecode = basecode;
        }
    }
}
