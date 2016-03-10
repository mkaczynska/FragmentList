package com.blstream.kaczynska.fragmentlist;


import android.app.FragmentManager;
import android.graphics.drawable.Drawable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements MyListFragment.OnHeadlineSelectedListener{
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            MyListFragment myListFragment = new MyListFragment();
            fragmentTransaction.add(R.id.fragment, myListFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onHeaderSelected(int position, List<Item> values) {

        Bundle bundle = new Bundle();
        bundle.putInt("imgId", values.get(position).getId());
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        if(isTablet(this)){
//            fragmentTransaction.add(R.id.descriptionImageView, fragment);
//            fragmentTransaction.commit();
//        }
//        else{
            fragmentTransaction.replace(R.id.fragment, fragment);
            fragmentTransaction.addToBackStack(null).commit();}
//    }

    public void switchContent(int id, Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(id, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

//public class MainActivity extends AppCompatActivity {
//    private final static int PICNO = 5;
//@Override
//protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_main);
//
//    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.items);
//    // w celach optymalizacji
//    recyclerView.setHasFixedSize(true);
//
//    // ustawiamy LayoutManagera
//    recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//    // ustawiamy animatora, który odpowiada za animację dodania/usunięcia elementów listy
//    recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//    // tworzymy źródło danych - tablicę z artykułami
//    ArrayList<Item> itemList = new ArrayList<>();
//    for (int i = 0; i < 20; ++i) {
//        Item item = new Item(i+1, Integer.toString(i));
//        item.setImage(getResources().getDrawable(R.drawable.earth));
////        setImageFromFile(item);
//        itemList.add(item);
//    }
//    // tworzymy adapter oraz łączymy go z RecyclerView
//    recyclerView.setAdapter(new MyAdapter(itemList, recyclerView));
//}
//    private void setImageFromFile(Item item) {
//        int choice = item.getId()%PICNO;
//        switch (choice) {
//            case 0:
//                item.setImage(getResources().getDrawable(R.drawable.earth));
//                break;
//            case 1:
//                item.setImage(getResources().getDrawable(R.drawable.jupiter));
//                break;
//            case 2:
//                item.setImage(getResources().getDrawable(R.drawable.moon));
//                break;
//            case 3:
//                item.setImage(getResources().getDrawable(R.drawable.pluto));
//                break;
//            case 4:
//                item.setImage(getResources().getDrawable(R.drawable.saturn));
//                break;
//        }
//    }
//}
