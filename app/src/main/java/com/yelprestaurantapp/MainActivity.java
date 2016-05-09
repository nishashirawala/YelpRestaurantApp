package com.yelprestaurantapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.yelprestaurantapp.adapter.RestaurantTableDataAdapter;
import com.yelprestaurantapp.bean.Restaurant;
import com.yelprestaurantapp.listener.RestaurantDataClickListener;
import com.yelprestaurantapp.service.RestaurantService;

import java.util.Comparator;
import java.util.List;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowColorizers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RestaurantServiceAsyncTask restaurantServiceAsyncTask = new RestaurantServiceAsyncTask();
        restaurantServiceAsyncTask.execute();
    }


    public void updateUI(List<Restaurant> list) {
        SortableTableView<Restaurant> sortableTableView = (SortableTableView<Restaurant>) findViewById(R.id.tableView);
        sortableTableView.setDataAdapter(new RestaurantTableDataAdapter(this, list));
        sortableTableView.setColumnComparator(0, new RestaurantNameComparator());
        sortableTableView.setColumnComparator(0, new RestaurantNameComparator());
        String nameHeader = getResources().getString(R.string.nameHeader);
        String addressHeader = getResources().getString(R.string.addressHeader);
        sortableTableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, new String[]{nameHeader, addressHeader}));
        int colorEvenRows = ContextCompat.getColor(this, R.color.white);
        int colorOddRows = ContextCompat.getColor(this, R.color.ltgray);
        sortableTableView.setDataRowColorizer(TableDataRowColorizers.alternatingRows(colorEvenRows, colorOddRows));
        sortableTableView.addDataClickListener(new RestaurantDataClickListener(this));
    }

    private static class RestaurantNameComparator implements Comparator<Restaurant> {
        @Override
        public int compare(Restaurant r1, Restaurant r2) {
            return r1.getName().compareTo(r2.getName());
        }
    }

    class RestaurantServiceAsyncTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            RestaurantService service = new RestaurantService();
            String location = getResources().getString(R.string.searchLocation);
            String limit = getResources().getString(R.string.limit);
            List<Restaurant> restaurantList = service.getRestaurants(location, limit);
            return restaurantList;
        }

        @Override
        protected void onPostExecute(Object object) {
            super.onPostExecute(object);
            List<Restaurant> restaurantList = (List<Restaurant>) object;
            MainActivity.this.updateUI(restaurantList);
        }
    }
}
