package com.example.zach.ad340_mainapp;

public class Camera {
    private String imageURL;
    private String desc;

    public Camera (String imageURL, String desc) {
        this.imageURL = imageURL;
        this.desc     = desc;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDesc() {
        return desc;
    }
}
