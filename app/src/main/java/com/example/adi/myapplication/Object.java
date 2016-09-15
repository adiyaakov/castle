package com.example.adi.myapplication;

/**
 * Created by Adi on 12/09/2016.
 */
public class Object {
    private String imgURL;
    private String item_name;
    private String item_description;
    private String item_price;

    public Object() {

    }
    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }



    public Object(String item_description, String item_name, String imgURL, String item_price) {
        this.item_description = item_description;
        this.item_name = item_name;
        this.imgURL = imgURL;
        this.item_price = item_price;
    }







}
