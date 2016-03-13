package com.blstream.kaczynska.fragmentlist;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;

/**
 * Created by user on 11-Mar-16.
 */
public class LRUCacheManager {

    private LruCache<String, Bitmap> mMemoryCache;

    public LRUCacheManager() {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }


    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
//            System.out.println("addBitmapToMemoryCache: " + key);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        Bitmap bitmap = mMemoryCache.get(key);
//        System.out.println("getBitmapFromMemCache: " + key);
        return bitmap;
    }

//    public void clearCache() {
//        mMemoryCache.evictAll();
//    }


}
