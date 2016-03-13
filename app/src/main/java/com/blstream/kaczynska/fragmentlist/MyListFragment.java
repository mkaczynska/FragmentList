package com.blstream.kaczynska.fragmentlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;


public class MyListFragment extends Fragment {

    ArrayList<Item> itemList;
    final static int LISTSIZE = 1000;
    ArrayList<String> resizedImageKeys;

    OnHeadlineSelectedListener mCallback;


    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnHeadlineSelectedListener {
        /**
         * Called by HeadlinesFragment when a list item is selected
         */
        public void onHeaderSelected(int position, List<Item> itemList);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemList = new ArrayList<>();
        resizedImageKeys = new ArrayList<>();
        String[] imagesArray = {
                "earth.jpg",
                "jupiter.jpg",
                "moon.jpg",
                "sun.jpg",
                "saturn.jpg"
        };


        for (int itemId = 0; itemId < LISTSIZE; ++itemId) {
            int choice = itemId % imagesArray.length;
            String imageName = imagesArray[choice];
            resizedImageKeys.add("tn_" + imageName);
            Item item = new Item(itemId + 1, imageName);
            itemList.add(item);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.mylistfragment_layout, container, false);

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.items);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new MyAdapter(context, itemList, resizedImageKeys, mCallback));

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        if (OnHeadlineSelectedListener.class.isInstance(context)) {
            mCallback = (OnHeadlineSelectedListener) context;
        }
        else {
            throw new IllegalStateException("Jak to sie stalo?");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }
}
