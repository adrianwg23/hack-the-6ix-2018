package com.example.adrianwong.yum.ui.restaurant;

import android.support.annotation.NonNull;

import com.example.adrianwong.yum.datamodel.RestaurantItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantInteractor {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private DatabaseReference restauarantRef = database.getReference("restaurants");

    private RestaurantPresenter restaurantPresenter;

    private List<RestaurantItem> mCurrentRestaurantList = new ArrayList<>();

    RestaurantInteractor(RestaurantPresenter presenter) {
        restaurantPresenter = presenter;
        retrieveRestaurantList();
    }

    private void retrieveRestaurantList() {
        restauarantRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mCurrentRestaurantList.clear();

                for (DataSnapshot restaurantSnapshot : dataSnapshot.getChildren()) {
                    String name = restaurantSnapshot.getKey();
                    mCurrentRestaurantList.add(new RestaurantItem(name));
                }

                restaurantPresenter.refreshRestaurantList(mCurrentRestaurantList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
