package com.android.up.model;

public class ConcertObject {
    private String title;
    private String details;
    private String image;
    private int price;
    private int id;


    public ConcertObject(String title, String details, String image, int price, int id) {
        this.title = title;
        this.details = details;
        this.image = image;
        this.price = price;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
