package com.android.up.model;

public class News {
    private String details;
    private String title;
    private String image;
    private int id;

    public News(String details, String title, String image, int id) {
        this.details = details;
        this.title = title;
        this.image = image;
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
