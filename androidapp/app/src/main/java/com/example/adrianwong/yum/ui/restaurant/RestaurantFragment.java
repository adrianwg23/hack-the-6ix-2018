package com.example.adrianwong.yum.ui.restaurant;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adrianwong.yum.R;
import com.example.adrianwong.yum.adapter.RestaurantAdapter;
import com.example.adrianwong.yum.datamodel.RestaurantItem;
import com.example.adrianwong.yum.ui.menu.MenuActivity;
import com.example.adrianwong.yum.ui.menu.MenuFragment;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFragment extends Fragment implements RestaurantContract.RestaurantView, RestaurantAdapter.InteractionListener {

    @BindView(R.id.restaurant_list)
    RecyclerView mRecyclerView;

    private RestaurantPresenter mRestaurantPresenter = new RestaurantPresenter();

    private RestaurantAdapter mRestaurantAdapter;



    public RestaurantFragment() {
        // Required empty public constructor
    }

    public static RestaurantFragment newInstance() {
        return new RestaurantFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRestaurantAdapter = new RestaurantAdapter();
        mRestaurantAdapter.setListInteractionListener(this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRestaurantPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRestaurantPresenter.detachView();
    }

    @Override
    public void initViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRestaurantAdapter);
    }

    @Override
    public void startMenuActivity() {
        Intent intent = new Intent(getActivity(), MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void refreshRestaurantList(List<RestaurantItem> restaurantItems) {
        mRestaurantAdapter.set
    }

    @Override
    public void onListClick(int restaurantId) {
        Intent intent = new Intent (getActivity(), MenuActivity.class);
        intent.putExtra("restaurantId", restaurantId);
        startActivity(intent);
    }
}
