package com.android.up.model;

public class Manager {
    private String username;
    private String first_name;
    private String last_name;
    private String pass;

    public Manager(String username, String first_name, String last_name, String pass) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
