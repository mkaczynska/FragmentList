package com.blstream.kaczynska.fragmentlist;


import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.util.List;

public class MainActivity extends AppCompatActivity implements MyListFragment.OnHeadlineSelectedListener {

    Fragment myListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isTablet(this)) {
            setContentView(R.layout.activity_main_tablet);
        }
        else {
            setContentView(R.layout.activity_main);
        }


        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            myListFragment = new MyListFragment();
            fragmentTransaction.add(R.id.fragment, myListFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
      protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "listFragment", myListFragment);
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        myListFragment = getSupportFragmentManager().getFragment(outState, "listFragment");
        super.onSaveInstanceState(outState);
    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        LRUCacheManager lruCacheManager = new LRUCacheManager();
//        lruCacheManager.clearCache();
//
//    }

    @Override
    public void onHeaderSelected(int position, List<Item> values) {

        DetailFragment fragment = new DetailFragment();

        Bundle bundle = new Bundle();
        Item item = values.get(position);
        bundle.putParcelable("selected_item", item);
        fragment.setArguments(bundle);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isTablet(this)) {
            fragmentTransaction.add(R.id.fragment, fragment);
            fragmentTransaction.commit();
        } else {
            fragmentTransaction.replace(R.id.fragment, fragment);
            fragmentTransaction.addToBackStack(null).commit();
        }
    }


    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        boolean large = ((context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

}



