package com.example.adrianwong.yum.datamodel;

public class OrderItem {

    private String sender;
    private String restaurant;
    private String order;

    public OrderItem(String sender, String restaurant, String order) {
        this.sender = sender;
        this.restaurant = restaurant;
        this.order = order;
    }

    public String getSender() {
        return sender;
    }

    public String getRestaurantTwo() {
        return restaurant;
    }

    public String getOrder() {
        return order;
    }
}
