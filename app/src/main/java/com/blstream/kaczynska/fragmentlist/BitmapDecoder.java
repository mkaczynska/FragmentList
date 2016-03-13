package com.blstream.kaczynska.fragmentlist;


import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;

public class BitmapDecoder {





    public static Bitmap decodeSampledBitmapFromAssets(AssetManager assetManager, String imageName, String imageKey, int outWidth, int outHeight) {
        Bitmap resizedBitmap = null;


        try {
            InputStream fileName = assetManager.open(imageName);

            if(!imageName.equals(imageKey)) {
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(fileName, null, options);

                options.inSampleSize = calculateInSampleSize(options, outWidth, outHeight);

                options.inJustDecodeBounds = false;
                resizedBitmap = BitmapFactory.decodeStream(fileName, null, options);
            }
            else {
                resizedBitmap = BitmapFactory.decodeStream(fileName);
            }


            LRUCacheManager lruCacheManager = new LRUCacheManager();
            lruCacheManager.addBitmapToMemoryCache(imageKey, resizedBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resizedBitmap;
    }

    private static int calculateInSampleSize (BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static void loadImage(AssetManager assetManager, String imageName, String imageKey, ImageView imageView, int outWidth, int outHeight) {

        LRUCacheManager lruCacheManager = new LRUCacheManager();
        final Bitmap bitmap = lruCacheManager.getBitmapFromMemCache(imageKey);
        if(bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        else {
            ImageLoaderParams asyncParams = new ImageLoaderParams(assetManager, imageName, imageKey, outWidth, outHeight);
            ImageLoaderAsyncTask oldAsyncLoaderTask = (ImageLoaderAsyncTask) imageView.getTag();
            if (oldAsyncLoaderTask != null) {
                oldAsyncLoaderTask.cancel(true);
            }
            ImageLoaderAsyncTask asyncLoaderTask = new ImageLoaderAsyncTask(imageView);
            asyncLoaderTask.execute(asyncParams);
            imageView.setTag(asyncLoaderTask);
        }
    }
}
