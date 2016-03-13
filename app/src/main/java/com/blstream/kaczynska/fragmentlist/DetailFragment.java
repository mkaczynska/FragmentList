package com.blstream.kaczynska.fragmentlist;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;



public class DetailFragment extends Fragment {

    ImageView detailedImageView;
    TextView detailedTextView;
    Item item;
    Context context;
    AssetManager assetManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        context = getContext();
        View view = inflater.inflate(R.layout.detailfragment_layout, container, false);
        Bundle bundle = this.getArguments();
        item = bundle.getParcelable("selected_item");

        detailedTextView = (TextView) view.findViewById(R.id.detailedItemTitle);
        detailedTextView.setText(item.getTitle());

        detailedImageView = (ImageView) view.findViewById(R.id.detailedImageView);
        assetManager = context.getAssets();

        adjustImageView();
        BitmapDecoder.loadImage(assetManager, item.getImageName(), item.getImageName(), detailedImageView, 0, 0);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putParcelable("selected_item", item);
    }


    private void adjustImageView() {

        Point imageViewSize = new Point();
        imageViewSize.x = 1920;
        imageViewSize.y = 1200;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(imageViewSize.x, imageViewSize.y);
        detailedImageView.setLayoutParams(params);
    }

}

