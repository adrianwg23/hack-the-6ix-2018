package com.example.adrianwong.yum.ui.order;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.adrianwong.yum.R;
import com.example.adrianwong.yum.ui.base.BaseActivity;
import com.example.adrianwong.yum.ui.restaurant.RestaurantFragment;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class OrderActivity extends BaseActivity {

    private static final String ORDER_TAG = "ORDER_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        FragmentManager manager = getSupportFragmentManager();
        OrderFragment fragment = (OrderFragment) manager.findFragmentByTag(ORDER_TAG);

        Intent intent = getIntent();


        if (fragment == null) {
            setTitle("Orders");
            fragment = OrderFragment.newInstance("Orders");
        }

        addFragmentToActivity(manager, fragment, R.id.root_order_layout, ORDER_TAG);
    }
}
