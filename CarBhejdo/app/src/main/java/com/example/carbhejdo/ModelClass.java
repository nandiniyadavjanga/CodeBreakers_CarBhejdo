package com.example.carbhejdo;

public class ModelClass {

    private int imageResource;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    private String title;
    private String body;
    private String body1;

    public ModelClass(String imageUrl, String title, String body, String body1) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.body = body;
        this.body1 = body1;
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
}

