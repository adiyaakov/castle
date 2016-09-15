package com.example.adi.myapplication.Item_strqture;

/**
 * Created by Adi on 21/07/2016.
 */
public class MenuItem {
    private int img_R;
    private String item_name;
    private String item_description;

    public MenuItem(String item_name, int img_R, String item_description) {
        this.img_R = img_R;
        this.item_name = item_name;
        this.item_description = item_description;



    }

    public MenuItem(){}

    public MenuItem(int img_R){
        this.img_R = img_R;
    }

    public int getImg_R() {
        return img_R;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_description() {
        return item_description;
    }
}


