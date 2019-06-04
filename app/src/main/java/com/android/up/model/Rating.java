package com.android.up.model;

public class Rating {
    private String username;
    private int id;
    private float rating;

    public Rating(String username, int id, float rating) {
        this.username = username;
        this.id = id;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
