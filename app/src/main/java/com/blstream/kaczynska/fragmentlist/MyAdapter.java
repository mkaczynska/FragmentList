package com.blstream.kaczynska.fragmentlist;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Item> itemList;
    private ArrayList<String> resizedImageKeys;
    private final MyListFragment.OnHeadlineSelectedListener mListener;
    Context context;
    private final static int RES = 100;
    public static AssetManager assetManager;


    public MyAdapter(Context context, ArrayList<Item> items, ArrayList<String> resizedImageKeys, MyListFragment.OnHeadlineSelectedListener listener) {
        itemList = items;
        mListener = listener;
        this.context = context;
        this.resizedImageKeys = resizedImageKeys;
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
        String imageKey = resizedImageKeys.get(position);

        BitmapDecoder.loadImage(assetManager, item.getImageName(), imageKey, viewHolder.image, RES, RES);

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


}
