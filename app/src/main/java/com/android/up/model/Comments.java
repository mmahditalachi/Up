package com.android.up.model;

public class Comments {
    private String username;
    private String text;
    private int product_id;
    private int like;
    private int dislike;

    public Comments(String username, String text, int product_id, int like, int dislike) {
        this.username = username;
        this.text = text;
        this.product_id = product_id;
        this.like = like;
        this.dislike = dislike;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }
}
