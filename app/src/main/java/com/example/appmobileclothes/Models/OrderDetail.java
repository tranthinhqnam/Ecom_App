package com.example.appmobileclothes.Models;

public class OrderDetail {
    private String id;
    private String order_id;
    private int prod_id;
    private String name;
    private int quantity;
    private int price;

    public OrderDetail() {
    }

    public OrderDetail(String id, String order_id, int prod_id, String name, int quantity, int price) {
        this.id = id;
        this.order_id = order_id;
        this.prod_id = prod_id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
