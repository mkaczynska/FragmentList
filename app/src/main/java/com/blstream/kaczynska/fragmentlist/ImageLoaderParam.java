package com.blstream.kaczynska.fragmentlist;

import android.widget.ImageView;

/**
 * Created by user on 11-Mar-16.
 */
public class ImageLoaderParam {
    String imageName;
    int outWidth;
    int outHeigth;

    public ImageLoaderParam(String imageName, int outWidth, int outHeigth) {
        this.imageName = imageName;
        this.outWidth = outWidth;
        this.outHeigth = outHeigth;
    }
}
