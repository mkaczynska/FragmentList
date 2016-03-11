package com.blstream.kaczynska.fragmentlist;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static com.blstream.kaczynska.fragmentlist.MyAdapter.loadImage;


public class DetailFragment extends Fragment {

    ImageView detailedImageView;
    TextView detailedTextView;
    Item item;
    private final static int RES = 100;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.detailfragment_layout, container, false);
        Bundle bundle = this.getArguments();
        item = bundle.getParcelable("selected_item");

        detailedTextView = (TextView) view.findViewById(R.id.detailedItemTitle);
        detailedTextView.setText(item.getTitle());

        detailedImageView = (ImageView) view.findViewById(R.id.detailedImageView);
        loadImage(item, detailedImageView);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putParcelable("selected_item", item);
    }

}
