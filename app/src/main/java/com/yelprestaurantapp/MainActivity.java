package com.yelprestaurantapp;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.yelprestaurantapp.adapter.RestaurantTableDataAdapter;
import com.yelprestaurantapp.asynctask.RestaurantServiceAsyncTask;
import com.yelprestaurantapp.bean.Restaurant;
import com.yelprestaurantapp.listener.RestaurantDataClickListener;
import com.yelprestaurantapp.service.RestaurantService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowColorizers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String location = getString(R.string.searchLocation);
        String limit = getString(R.string.limit);
        String lat = getString(R.string.lat);
        String lon = getString(R.string.lon);

        String[] params = {location, limit, lat, lon};
        RestaurantServiceAsyncTask restaurantServiceAsyncTask = new RestaurantServiceAsyncTask(this);
        restaurantServiceAsyncTask.execute(params);
    }

    public void updateUI(List<Restaurant> list) {
        if(list != null && list.size()>0) {
            SortableTableView<Restaurant> sortableTableView = (SortableTableView<Restaurant>) findViewById(R.id.tableView);
            sortableTableView.setDataAdapter(new RestaurantTableDataAdapter(this, list));
            sortableTableView.setColumnComparator(0, new RestaurantNameComparator());
            String nameHeader = getString(R.string.nameHeader);
            String addressHeader = getString(R.string.addressHeader);
            sortableTableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, new String[]{nameHeader, addressHeader}));
            int colorEvenRows = getResources().getColor(R.color.white);
            int colorOddRows = getResources().getColor(R.color.ltgray);
            sortableTableView.setDataRowColorizer(TableDataRowColorizers.alternatingRows(colorEvenRows, colorOddRows));
            sortableTableView.addDataClickListener(new RestaurantDataClickListener(this));
        } else {
            showErrorMsg(getResources().getString(R.string.empty_restaurant_list));
        }
    }

    static class RestaurantNameComparator implements Comparator<Restaurant> {
        @Override
        public int compare(Restaurant r1, Restaurant r2) {
            return r1.getName().compareTo(r2.getName());
        }
    }

    private void showErrorMsg(String errorMsg) {
        TableView layout = (TableView) findViewById(R.id.tableView);
        layout.removeAllViews();
        TextView errorTextView = new TextView(this);
        errorTextView.setTextColor(Color.RED);
        errorTextView.setText(errorMsg);
        errorTextView.setPadding(10, 10, 10, 10);
        layout.addView(errorTextView);
        Log.e("Error", errorMsg);
    }
}
