package com.android.up.model;

public class Users {
    private String username;
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private String pass;
    private String number;
    private String phonenum;
    private String cardnum;
    private double address_latitude;
    private double address_Longitude;
    private int cardInventory;

    public Users(String username, int id, String first_name, String last_name, String email, String address, String pass, String number, String phonenum, String cardnum, double address_latitude, double address_Longitude, int cardInventory) {
        this.username = username;
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.address = address;
        this.pass = pass;
        this.number = number;
        this.phonenum = phonenum;
        this.cardnum = cardnum;
        this.address_latitude = address_latitude;
        this.address_Longitude = address_Longitude;
        this.cardInventory = cardInventory;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public double getAddress_latitude() {
        return address_latitude;
    }

    public void setAddress_latitude(double address_latitude) {
        this.address_latitude = address_latitude;
    }

    public double getAddress_Longitude() {
        return address_Longitude;
    }

    public void setAddress_Longitude(double address_Longitude) {
        this.address_Longitude = address_Longitude;
    }

    public int getCardInventory() {
        return cardInventory;
    }

    public void setCardInventory(int cardInventory) {
        this.cardInventory = cardInventory;
    }
}
