package com.example.adrianwong.yum.ui.restaurant;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.adrianwong.yum.datamodel.RestaurantItem;
import com.example.adrianwong.yum.ui.base.BasePresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantPresenter extends BasePresenter<RestaurantContract.RestaurantView> {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private DatabaseReference restauarantRef = database.getReference("restaurants");
    private List<RestaurantItem> mCurrentRestaurantList = new ArrayList<>();


    public RestaurantPresenter() {
    }

    public void getList() {
        restauarantRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mCurrentRestaurantList.clear();

                for (DataSnapshot restaurantSnapshot : dataSnapshot.getChildren()) {
                    String name = restaurantSnapshot.getKey();
                    Log.d("RestaurantPresenter", name);
                    mCurrentRestaurantList.add(new RestaurantItem(name));
                }

                mView.refreshRestaurantList(mCurrentRestaurantList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
