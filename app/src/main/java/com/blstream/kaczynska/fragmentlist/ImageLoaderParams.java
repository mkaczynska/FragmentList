package com.blstream.kaczynska.fragmentlist;

import android.content.res.AssetManager;
import android.widget.ImageView;

/**
 * Created by user on 11-Mar-16.
 */
public class ImageLoaderParams {
    String imageName;
    String imageKey;
    int outWidth;
    int outHeight;
    AssetManager assetManager;

    public ImageLoaderParams(AssetManager assetManager, String imageName, String imageKey, int outWidth, int outHeight) {
        this.imageName = imageName;
        this.imageKey = imageKey;
        this.outWidth = outWidth;
        this.outHeight = outHeight;
        this.assetManager = assetManager;
    }
}
