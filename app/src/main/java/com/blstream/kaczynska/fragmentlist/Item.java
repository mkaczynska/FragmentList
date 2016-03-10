package com.blstream.kaczynska.fragmentlist;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by user on 10-Mar-16.
 */
public class Item implements Serializable {

    private int id;
    private String title;
    private Drawable image;

    public int getId() {
        return id;
    }



    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }


    public Item(int id, String title) {
        this.id = id;
        this.title = title;
//        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
