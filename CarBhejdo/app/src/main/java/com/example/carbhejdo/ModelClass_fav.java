package com.example.carbhejdo;

public class ModelClass_fav {
    private String imageResource;
    private String title;
    private String body;
    private String imageUrl;

    public ModelClass_fav(String imageUrl, String title, String body) {
       // this.imageResource = imageResource;
        this.title = title;
        this.body = body;
        this.imageUrl = imageUrl;
    }

    public String getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public String getBody() {
        return body;
    }
}
