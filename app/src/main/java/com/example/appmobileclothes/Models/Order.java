package com.example.appmobileclothes.Models;

public class Order {
    private String id;
    private String date;
    private String title;
    private String user_id;
    private String total;

    public Order() {
    }

    public Order(String id, String date, String title, String user_id, String total) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.user_id = user_id;
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}

