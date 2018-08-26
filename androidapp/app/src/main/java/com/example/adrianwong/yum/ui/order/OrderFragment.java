package com.example.adrianwong.yum.ui.order;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adrianwong.yum.R;
import com.example.adrianwong.yum.adapter.OrderAdapter;
import com.example.adrianwong.yum.datamodel.OrderItem;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {

    public static final String EXTRA_NAME = "restaurantName";

    @BindView(R.id.order_rec_view)
    RecyclerView mRecyclerView;

    private  List<OrderItem> orderItemList = new ArrayList<>();

    private OrderAdapter orderAdapter;
    private OrderPresenter orderPresenter;


    private Emitter.Listener onNewOrder = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject data = (JSONObject) args[0];
                        String sender;
                        String restaurant;
                        String order;

                        try {
                            sender = data.getString("sender");
                            restaurant = data.getString("restaurant");
                            order = data.getString("order");
                        } catch (JSONException e) {
                            return;
                        }

                        OrderItem orderItem = new OrderItem(sender, restaurant, order);
                        orderItemList.add(orderItem);

                        // add the message to view
                        refreshOrderList(orderItemList);
                    }
                });
            }

        }
    };


    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://5bbee879.ngrok.io");
        } catch (URISyntaxException e) {}
    }



    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance(String restaurantName) {
        OrderFragment fragment = new OrderFragment();

        Bundle args = new Bundle();
        args.putString(EXTRA_NAME, restaurantName);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, rootView);

        orderAdapter = new OrderAdapter();

        initViews();

        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mSocket.on("ordered", onNewOrder);
        mSocket.connect();
        if (mSocket.connected()) {
            Toast.makeText(getContext(), "Connected!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Nope :(", Toast.LENGTH_SHORT).show();
        }

        refreshOrderList(orderItemList);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void initViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(orderAdapter);
    }

    public void refreshOrderList(List<OrderItem> orderItems) {
        orderAdapter.setList(orderItems);
    }
}
