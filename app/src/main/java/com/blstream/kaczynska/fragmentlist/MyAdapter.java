package com.blstream.kaczynska.fragmentlist;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 10-Mar-16.
 */
public class MyAdapter extends RecyclerView.Adapter {

    // źródło danych
    private ArrayList<Item> itemList;
    Bundle bundle;
    Context context;
    DetailFragment fragment;
    // obiekt listy artykułów
//    private RecyclerView mRecyclerView;

    private final MyListFragment.OnHeadlineSelectedListener mListener;

    // implementacja wzorca ViewHolder
    // każdy obiekt tej klasy przechowuje odniesienie do layoutu elementu listy
    // dzięki temu wywołujemy findViewById() tylko raz dla każdego elementu
    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public ImageView imageView;

        public MyViewHolder(View pItem) {
            super(pItem);
            titleTextView = (TextView) pItem.findViewById(R.id.itemTitle);
            imageView = (ImageView) pItem.findViewById(R.id.itemImage);
        }
    }

    // konstruktor adaptera
    public MyAdapter(ArrayList<Item> items, MyListFragment.OnHeadlineSelectedListener listener) {
        itemList = items;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        // tworzymy layout artykułu oraz obiekt ViewHoldera
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout, viewGroup, false);

//        // dla elementu listy ustawiamy obiekt OnClickListener,
//        // który usunie element z listy po kliknięciu na niego
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // odnajdujemy indeks klikniętego elementu
//                int positionToDelete = mRecyclerView.getChildAdapterPosition(v);
//                // usuwamy element ze źródła danych
//                itemList.remove(positionToDelete);
//                // poniższa metoda w animowany sposób usunie element z listy
//                notifyItemRemoved(positionToDelete);
//            }
//        });

        // tworzymy i zwracamy obiekt ViewHolder
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        // uzupełniamy layout artykułu
        final Item item = itemList.get(i);
        ((MyViewHolder) viewHolder).titleTextView.setText(item.getTitle());
        ((MyViewHolder) viewHolder).imageView.setImageDrawable(item.getImage());
        ((MyViewHolder) viewHolder).titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentJump(item);
            }
        });
    }

    private void fragmentJump(Item itemSelected) {
        if (fragment == null) {
            fragment = new DetailFragment();
            bundle = new Bundle();
            bundle.putSerializable("item_selected_key", itemSelected);
            fragment.setArguments(bundle);
//        bundle.putParcelable("item_selected_key", (Parcelable) itemSelected);
            switchContent(R.id.fragment, fragment);
        }
    }

    public void switchContent(int id, Fragment fragment) {
        if (context == null)
            return;
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.switchContent(id, fragment);
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
