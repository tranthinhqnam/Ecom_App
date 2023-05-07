package com.example.appmobileclothes.Models;

public class Category {
    private int id;
    private String name;
    private String img_name;

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public String getName() {
        return name;
    }

    public void setName(String newTitle_photo) {
        this.name = newTitle_photo;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String newSource_photo) {
        this.img_name = newSource_photo;
    }

    public Category() {

    }

    public Category(int id, String category_name, String name) {
        this.id = id;
        this.name = category_name;
        this.img_name = name;
    }
}
