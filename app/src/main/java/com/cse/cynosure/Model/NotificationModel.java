package com.cse.cynosure.Model;

import android.widget.ImageView;

public class NotificationModel {
    public NotificationModel(){

    }
    private String text,time;
    private String  image;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
