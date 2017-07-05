package com.wtcrmandroid.model.reponsedata;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zxd on 2017/6/9
 */

public class CommentData {

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
