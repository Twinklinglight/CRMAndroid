package com.wtcrmandroid.model.reponsedata;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zxd on 2017/6/9
 */

public class CommentData {

    /**
        "id": "104867",
            "LogID": "394856",
            "userId": "1558",
            "userName": "邴万丹",
            "RoleName": "电话营销部部长(物通)",
            "content": "已经查看，继续努力！加油！",
            "time": "2017/7/25 19:41:27",
            "leve": "良好"
    */

    @SerializedName("userName")
    private String CommentPerson;
    @SerializedName("RoleName")
    private String CommentJob;
    @SerializedName("time")
    private String CommentTime;
    @SerializedName("content")
    private String CommentContent;

    private String id;
    private String LogID;
    private String userId;
    private String leve;

    public String getLeve() {
        return leve;
    }

    public void setLeve(String leve) {
        this.leve = leve;
    }

    public String getCommentPerson() {
        return CommentPerson;
    }

    public void setCommentPerson(String commentPerson) {
        CommentPerson = commentPerson;
    }

    public String getCommentJob() {
        return CommentJob;
    }

    public void setCommentJob(String commentJob) {
        CommentJob = commentJob;
    }

    public String getCommentTime() {
        return CommentTime;
    }

    public void setCommentTime(String commentTime) {
        CommentTime = commentTime;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String commentContent) {
        CommentContent = commentContent;
    }
}
