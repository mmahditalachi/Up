package com.android.up.model;

public class MobileBill {
    private String number;
    private int id;
    private int price;

    public MobileBill(String number, int id, int price) {
        this.number = number;
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
