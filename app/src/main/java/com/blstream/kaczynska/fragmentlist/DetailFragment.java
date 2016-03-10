package com.blstream.kaczynska.fragmentlist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by user on 10-Mar-16.
 */
public class DetailFragment extends Fragment {

    ImageView detailedImageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detailfragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            Item item = (Item) savedInstanceState.getSerializable("item_selected_key");
            detailedImageView = (ImageView) getActivity().findViewById(R.id.detailedImageView);
            detailedImageView.setImageDrawable(item.getImage());
        }
    }

}
