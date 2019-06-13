package com.android.up.model;

public class BillObject {
    private int price;
    private String number;
    private int id;
    private String categories;

    public BillObject(int price, String number, int id, String categories) {
        this.price = price;
        this.number = number;
        this.id = id;
        this.categories = categories;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
