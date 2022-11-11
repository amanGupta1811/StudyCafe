package com.android.studycafetask;

import com.google.firebase.Timestamp;

public class Task {
    String title;
    String detail;
    String completionDate;
    com.google.firebase.Timestamp timestamp;


    public Task() {

    }

    public String getTitleT() {
        return title;
    }

    public void setTitleT(String titleT) {
        this.title = titleT;
    }

    public String getDetailT() {
        return detail;
    }

    public void setDetailT(String detailT) {
        this.detail = detailT;
    }

    public String getCompletionDateT(){return completionDate;}

    public void setCompletionDateT(String completionDateT){this.completionDate=completionDateT;}

    public Timestamp getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(com.google.firebase.Timestamp timestamp) {
        this.timestamp = timestamp;
    }



}
