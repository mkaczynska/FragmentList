package com.blstream.kaczynska.fragmentlist;
import java.io.Serializable;


public class Item implements Serializable {

    private int id;
    private String title;
    private int image;

    public int getId() {
        return id;
    }



    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public Item(int id, int image) {
        this.id = id;
        this.title = "Element no " + id;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
