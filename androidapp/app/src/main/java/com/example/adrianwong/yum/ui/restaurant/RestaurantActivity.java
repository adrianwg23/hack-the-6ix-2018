package com.example.adrianwong.yum.ui.restaurant;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.adrianwong.yum.R;
import com.example.adrianwong.yum.ui.base.BaseActivity;

public class RestaurantActivity extends BaseActivity {

    private static final String RESTAURANT_TAG = "RESTAURANT_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        FragmentManager manager = getSupportFragmentManager();
        RestaurantFragment fragment = (RestaurantFragment) manager.findFragmentByTag(RESTAURANT_TAG);

        if (fragment == null) {
            fragment = RestaurantFragment.newInstance();
        }

        addFragmentToActivity(manager, fragment, R.id.root_activity_list, RESTAURANT_TAG);
    }
}
