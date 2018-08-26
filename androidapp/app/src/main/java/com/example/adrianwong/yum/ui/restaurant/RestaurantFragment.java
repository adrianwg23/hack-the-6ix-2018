package com.example.adrianwong.yum.ui.restaurant;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adrianwong.yum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFragment extends Fragment implements RestaurantContract.RestaurantView {

    private RestaurantPresenter mRestaurantPresenter = new RestaurantPresenter();


    public RestaurantFragment() {
        // Required empty public constructor
    }

    public static RestaurantFragment newInstance() {
        return new RestaurantFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRestaurantPresenter.attachView(this);
    }

    @Override
    public void initViews() {

    }
}
