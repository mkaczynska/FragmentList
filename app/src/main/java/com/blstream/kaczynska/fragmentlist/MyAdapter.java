package com.blstream.kaczynska.fragmentlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Item> itemList;
    private final MyListFragment.OnHeadlineSelectedListener mListener;


    public MyAdapter(ArrayList<Item> items, MyListFragment.OnHeadlineSelectedListener listener) {
        itemList = items;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int position) {

        viewHolder.title.setText(itemList.get(position).getTitle());
//        viewHolder.image.setImageResource(itemList.get(position).getImage());

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
}
