package com.example.carbhejdo;

import java.io.Serializable;

public class ModelClass implements Serializable {

    private int imageResource;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    private String title;
    private String body;
    private String body1;
    private String location;
    private String model_no;
    private String year;
    private String user_object_id;

    public ModelClass(String imageUrl, String title, String body, String body1, String location, String model_no, String year, String user_object_id) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.body = body;
        this.body1 = body1;
        this.location = location;
        this.model_no = model_no;
        this.year = year;
        this.user_object_id = user_object_id;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getBody1() {
        return body1;
    }

    public String getLocation() { return location; }

    public String getModel_no() { return model_no; }

    public String getYear() { return year; }

    public String getUser_object_id() { return user_object_id; }
}
