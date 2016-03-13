package com.blstream.kaczynska.fragmentlist;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import java.lang.ref.WeakReference;


public class ImageLoaderAsyncTask extends AsyncTask<ImageLoaderParams, Void, Bitmap> {

    String imageName;
    String imageKey;
    int outWidth;
    int outHeight;
    AssetManager assetManager;

    private final WeakReference<ImageView> imageViewReference;

    public ImageLoaderAsyncTask(ImageView imageView) {
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected Bitmap doInBackground(ImageLoaderParams... params) {
        imageName = params[0].imageName;
        imageKey = params[0].imageKey;
        outWidth = params[0].outWidth;
        outHeight = params[0].outHeight;
        assetManager = params[0].assetManager;


        return BitmapDecoder.decodeSampledBitmapFromAssets(assetManager, imageName, imageKey, outWidth, outHeight);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        final ImageView imageView = imageViewReference.get();
        if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
    }
}
