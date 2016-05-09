package com.yelprestaurantapp.listener;

import android.content.Context;
import android.content.Intent;

import com.yelprestaurantapp.DetailActivity;
import com.yelprestaurantapp.bean.Restaurant;

import de.codecrafters.tableview.listeners.TableDataClickListener;

public class RestaurantDataClickListener implements TableDataClickListener<Restaurant> {

    private Context mContext;

    public RestaurantDataClickListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void onDataClicked(int rowIndex, Restaurant clickedData) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra("businessId", clickedData.getId());
        mContext.startActivity(intent);
    }
}
