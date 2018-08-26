package com.example.adrianwong.yum.ui.restaurant;

import com.example.adrianwong.yum.datamodel.RestaurantItem;
import com.example.adrianwong.yum.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class RestaurantPresenter extends BasePresenter<RestaurantContract.RestaurantView> {

    private RestaurantInteractor interactor;

    public RestaurantPresenter() {
        interactor = new RestaurantInteractor(this);
    }

    public void refreshRestaurantList(List<RestaurantItem> restaurantItems) {
        mView.refreshRestaurantList(restaurantItems);
    }

}
