package com.blstream.kaczynska.fragmentlist;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import static com.blstream.kaczynska.fragmentlist.MyAdapter.decodeSampledBitmapFromDrawable;


public class ImageLoaderFromAssets extends AsyncTask<ImageLoaderParam, Void, Bitmap> {

    String imageName;
    int outWidth;
    int outHeight;

    private final WeakReference<ImageView> imageViewReference;

    public ImageLoaderFromAssets(ImageView imageView) {
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected Bitmap doInBackground(ImageLoaderParam... params) {
        imageName = params[0].imageName;
        outWidth = params[0].outWidth;
        outHeight = params[0].outHeigth;

        return decodeSampledBitmapFromDrawable(imageName, outWidth, outHeight);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        final ImageView imageView = imageViewReference.get();
        if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
    }





}
