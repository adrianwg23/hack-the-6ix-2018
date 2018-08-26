package com.example.adrianwong.yum.datamodel;

import java.util.List;

public class RestaurantItem {

    private String restaurantName;
    private List<MenuObject> menuObjectList;



    public RestaurantItem(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public List<MenuObject> getMenuObjectList() {
        return menuObjectList;
    }
}

