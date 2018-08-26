package com.example.adrianwong.yum.ui.restaurant;

import com.example.adrianwong.yum.datamodel.RestaurantItem;

import java.util.List;

public interface RestaurantContract {

    interface RestaurantView {
        void initViews();
        void startMenuActivity();

        void refreshRestaurantList(List<RestaurantItem> restaurantItems);
    }

}
