package com.yelprestaurantapp.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yelprestaurantapp.bean.Restaurant;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

public class RestaurantTableDataAdapter extends TableDataAdapter<Restaurant> {

    public RestaurantTableDataAdapter(Context context, List<Restaurant> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Restaurant restaurant = getRowData(rowIndex);
        View renderedView = null;
        switch (columnIndex) {
            case 0:
                renderedView = renderName(restaurant);
                break;
            case 1:
                renderedView = renderAddress(restaurant);
                break;
        }
        return renderedView;
    }

    private View renderName(Restaurant r) {
        TextView textView1 = new TextView(this.getContext());
        textView1.setText(r.getName());
        textView1.setContentDescription(r.getName());
        textView1.setPadding(20, 20, 20, 20);
        return textView1;
    }

    private View renderAddress(Restaurant r) {
        TextView textView1 = new TextView(this.getContext());
        textView1.setText(r.getAddress());
        textView1.setPadding(20, 20, 20, 20);
        return textView1;
    }
}
