package com.example.adi.myapplication.Item_strqture;

/**
 * Created by Adi on 24/07/2016.
 */
public class Item {
    private int img;
    private String item_name;
    private String item_description;
    private String item_price;

    public Item(int img, String item_name, String item_description, String item_price) {
        this.img = img;
        this.item_name = item_name;
        this.item_description = item_description;
        this.item_price = item_price;
    }
    public Item(int img, String item_name, String item_price) {
        this.img = img;
        this.item_name = item_name;
        this.item_price = item_price;
    }

    public String getItem_description() {
        return item_description;
    }

    public String getItem_name() {
        return item_name;
    }

    public int getImg() {
        return img;
    }

    public String getItem_price() {
        return item_price;
    }
}
