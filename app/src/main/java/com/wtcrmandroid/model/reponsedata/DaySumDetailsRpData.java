package com.wtcrmandroid.model.reponsedata;

import java.util.List;

/**
 * Created by zxd on 2017/7/3.
 */

public class DaySumDetailsRpData {

    private List<HtDaysumDetailsData> work;
    private List<CommentData> exam;

    private String leve;
    private String learning;

    public String getLeve() {
        return leve;
    }

    public void setLeve(String leve) {
        this.leve = leve;
    }

    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }

    public List<HtDaysumDetailsData> getWork() {
        return work;
    }

    public void setWork(List<HtDaysumDetailsData> work) {
        this.work = work;
    }

    public List<CommentData> getExam() {
        return exam;
    }

    public void setExam(List<CommentData> exam) {
        this.exam = exam;
    }

    /*public static class WorkBean {
        *//**
         * workLevel : A
         * workContent : 根据张枫霖的介绍对车主版代码进行了解
         * workUser : 申中佳
         * workState : 完成
         * workUnfinishedReason :
         * workFinishTime : 12:00
         * workNextFinishTime :
         *//*

        private String workLevel;
        private String workContent;
        private String workUser;
        private String workState;
        private String workUnfinishedReason;
        private String workFinishTime;
        private String workNextFinishTime;

        public String getWorkLevel() {
            return workLevel;
        }

        public void setWorkLevel(String workLevel) {
            this.workLevel = workLevel;
        }

        public String getWorkContent() {
            return workContent;
        }

        public void setWorkContent(String workContent) {
            this.workContent = workContent;
        }

        public String getWorkUser() {
            return workUser;
        }

        public void setWorkUser(String workUser) {
            this.workUser = workUser;
        }

        public String getWorkState() {
            return workState;
        }

        public void setWorkState(String workState) {
            this.workState = workState;
        }

        public String getWorkUnfinishedReason() {
            return workUnfinishedReason;
        }

        public void setWorkUnfinishedReason(String workUnfinishedReason) {
            this.workUnfinishedReason = workUnfinishedReason;
        }

        public String getWorkFinishTime() {
            return workFinishTime;
        }

        public void setWorkFinishTime(String workFinishTime) {
            this.workFinishTime = workFinishTime;
        }

        public String getWorkNextFinishTime() {
            return workNextFinishTime;
        }

        public void setWorkNextFinishTime(String workNextFinishTime) {
            this.workNextFinishTime = workNextFinishTime;
        }
    }

    public static class ExamBean {
        *//**
         * id : 100614
         * LogID : 385316
         * userId : 1776
         * userName : 樊恰蕴
         * RoleName : 移动事业部部长
         * content : 已经查看，继续努力！加油！ 有问题及时跟我说！; 车主版快点全面熟悉
         * time : 2017/6/13 18:35:46
         *//*

        private String id;
        private String LogID;
        private String userId;
        private String userName;
        private String RoleName;
        private String content;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogID() {
            return LogID;
        }

        public void setLogID(String LogID) {
            this.LogID = LogID;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getRoleName() {
            return RoleName;
        }

        public void setRoleName(String RoleName) {
            this.RoleName = RoleName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }*/
}
