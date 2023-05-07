package com.example.appmobileclothes.Models;

public class Cart {
    private String id;
    private String user_id;
    private int prod_id;
    private int quantity;

    public Cart() {
    }

    public Cart(String id, String user_id, int prod_id, int quantity) {
        this.id = id;
        this.user_id = user_id;
        this.prod_id = prod_id;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

