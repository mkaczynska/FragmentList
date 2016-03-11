package com.blstream.kaczynska.fragmentlist;
import android.os.Parcel;
import android.os.Parcelable;



public class Item implements Parcelable {

    private int id;
    private String title;
    private String imageName;

    public int getId() {
        return id;
    }



    public String getImage() {
        return imageName;
    }

    public void setImage(String image) {
        this.imageName = image;
    }


    public Item(int id, String image) {
        this.id = id;
        this.title = "Element no " + id;
        this.imageName = image;
    }

    public Item(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.imageName = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(imageName);
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {

        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
