package com.example.adi.myapplication.Item_strqture;

/**
 * Created by Adi on 23/07/2016.
 */
public class Catagory {

    private String catagory_name;
    private int img_int;

    public Catagory(String catagory_name, int img_int){
        this.catagory_name = catagory_name;
        this.img_int = img_int;
    }

    public int getImg_int() {
        return img_int;
    }

    public String getCatagory_name() {
        return catagory_name;
    }
}
