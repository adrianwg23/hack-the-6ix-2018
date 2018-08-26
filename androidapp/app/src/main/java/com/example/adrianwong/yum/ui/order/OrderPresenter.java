package com.example.adrianwong.yum.ui.order;

import com.example.adrianwong.yum.ui.base.BasePresenter;

public class OrderPresenter {

    private OrderFragment mView;

    public OrderPresenter(OrderFragment view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
    }

    public void startEmitting() {

    }
}
