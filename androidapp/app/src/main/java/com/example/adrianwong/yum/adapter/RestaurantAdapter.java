package com.example.adrianwong.yum.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adrianwong.yum.R;
import com.example.adrianwong.yum.datamodel.RestaurantItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantItemViewHolder> {

    private List<RestaurantItem> mRestaurantList;
    private InteractionListener mListInteractionListener;


    public RestaurantAdapter() {
        mRestaurantList = new ArrayList<>();
        mListInteractionListener = null;
    }

    @NonNull
    @Override
    public RestaurantItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantItemViewHolder holder, int position) {
        RestaurantItem restaurantItem = mRestaurantList.get(position);

        String restaurantName = restaurantItem.getRestaurantName();

        holder.mRestaurantNameTv.setText(restaurantName);

    }

    @Override
    public int getItemCount() {
        return mRestaurantList.size();
    }

    public void setList(List<RestaurantItem> restaurantItemList) {
        mRestaurantList = restaurantItemList;
        notifyDataSetChanged();
    }

    public interface InteractionListener {
        void onListClick(String restaurantName);
    }

    public void setListInteractionListener(InteractionListener interactionListener) {
        mListInteractionListener = interactionListener;
    }

    public class RestaurantItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_restaurant_name)
        TextView mRestaurantNameTv;

        public RestaurantItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListInteractionListener != null) {
                String restaurantName = mRestaurantList.get(getAdapterPosition()).getRestaurantName();
                mListInteractionListener.onListClick(restaurantName);
            }
        }
    }
}
