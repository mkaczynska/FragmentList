package com.blstream.kaczynska.fragmentlist;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Item> itemList;
    private final MyListFragment.OnHeadlineSelectedListener mListener;
    Context context;
    private final static int RES = 50;
    public static AssetManager assetManager;


    public MyAdapter(Context context, ArrayList<Item> items, MyListFragment.OnHeadlineSelectedListener listener) {
        itemList = items;
        mListener = listener;
        this.context = context;
        assetManager = context.getAssets();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int position) {

        Item item = itemList.get(position);
        viewHolder.title.setText(item.getTitle());

        loadImage(item, viewHolder.image);

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mListener) {
                    mListener.onHeaderSelected(position, itemList);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView title;
        public final ImageView image;
        public String stringItem;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) view.findViewById(R.id.itemTitle);
            image = (ImageView) view.findViewById(R.id.itemImage);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }


    public static Bitmap decodeSampledBitmapFromDrawable(String imageName,
                                                         int outWidth, int outHeight) {

        Drawable d = null;
        Bitmap resizedBitmap = null;

        try {
            d = Drawable.createFromStream(assetManager.open(imageName), null); //FIXME ???
            Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
            resizedBitmap = Bitmap.createScaledBitmap(bitmap, outWidth, outHeight, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resizedBitmap;
    }


    public static void loadImage(Item item, ImageView imageView) {

        ImageLoaderParam asyncParams = new ImageLoaderParam(item.getImage(), RES, RES);
        ImageLoaderFromAssets oldAsyncLoaderTask = (ImageLoaderFromAssets) imageView.getTag();

        if (oldAsyncLoaderTask != null) {
            oldAsyncLoaderTask.cancel(true);
        }

        ImageLoaderFromAssets asyncLoaderTask = new ImageLoaderFromAssets(imageView);
        asyncLoaderTask.execute(asyncParams);
        imageView.setTag(asyncLoaderTask);


//        ImageLoaderParam asyncParams = new ImageLoaderParam(item.getImage(), RES, RES); // TODO rozmiar powinien byc wyciagany z rozmiaru kontrolki
//        ImageLoaderFromAssets asyncLoaderTask = new ImageLoaderFromAssets(imageView);
//        asyncLoaderTask.execute(asyncParams);
    }
}
