package com.example.adrianwong.yum.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adrianwong.yum.R;
import com.example.adrianwong.yum.datamodel.OrderItem;
import com.example.adrianwong.yum.datamodel.RestaurantItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderItemViewHolder> {

    private List<OrderItem> orderItemList;

    public OrderAdapter() {
        orderItemList = new ArrayList<>();
    }


    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        OrderItem orderItem = orderItemList.get(position);

        String phoneNumber = orderItem.getSender();
        String restaurant = orderItem.getRestaurantTwo();
        String item = orderItem.getOrder();

        holder.mPhoneNumberTv.setText(phoneNumber);
        holder.mRestaurantNameTv.setText(restaurant);
        holder.mItemNameTv.setText(item);
    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    public void setList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
        notifyDataSetChanged();
    }

    public class OrderItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_phone_number)
        TextView mPhoneNumberTv;

        @BindView(R.id.tv_restaurant)
        TextView mRestaurantNameTv;

        @BindView(R.id.tv_item_name)
        TextView mItemNameTv;

        public OrderItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
