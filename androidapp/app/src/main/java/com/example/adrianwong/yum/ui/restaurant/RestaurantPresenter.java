package com.example.adrianwong.yum.ui.restaurant;

import com.example.adrianwong.yum.datamodel.RestaurantItem;
import com.example.adrianwong.yum.ui.base.BasePresenter;

import java.util.ArrayList;

public class RestaurantPresenter extends BasePresenter<RestaurantContract.RestaurantView> {

    private RestaurantInteractor interactor;

    public RestaurantPresenter() {
        interactor = new RestaurantInteractor(this);
    }

    public void refreshRestaurantList(ArrayList<RestaurantItem> restaurantItems) {

    }

}
