package com.example.appmobileclothes.Models;

public class Banner {
    private int id;
    private String img_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    public Banner() {

    }

    public Banner(int id, String photo_source) {
        this.id = id;
        this.img_name = photo_source;
    }
}
