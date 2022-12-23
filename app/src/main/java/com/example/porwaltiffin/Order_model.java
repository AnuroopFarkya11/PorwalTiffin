package com.example.porwaltiffin;

public class Order_model {

    String ord_name;
    String ord_price;
    int ord_cnt;
    int ord_image;

    public Order_model(String ord_name, String ord_price, int ord_cnt, int ord_image) {
        this.ord_name = ord_name;
        this.ord_price = ord_price;
        this.ord_cnt = ord_cnt;
        this.ord_image = ord_image;
    }

    public String getOrd_name() {
        return ord_name;
    }

    public void setOrd_name(String ord_name) {
        this.ord_name = ord_name;
    }

    public String getOrd_price() {
        return ord_price;
    }

    public void setOrd_price(String ord_price) {
        this.ord_price = ord_price;
    }

    public int getOrd_cnt() {
        return ord_cnt;
    }

    public void setOrd_cnt(int ord_cnt) {
        this.ord_cnt = ord_cnt;
    }

    public int getOrd_image() {
        return ord_image;
    }

    public void setOrd_image(int ord_image) {
        this.ord_image = ord_image;
    }
}
