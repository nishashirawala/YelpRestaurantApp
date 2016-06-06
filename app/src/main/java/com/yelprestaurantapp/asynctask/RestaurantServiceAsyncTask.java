package com.yelprestaurantapp.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.yelprestaurantapp.MainActivity;
import com.yelprestaurantapp.R;
import com.yelprestaurantapp.bean.Restaurant;
import com.yelprestaurantapp.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

public class RestaurantServiceAsyncTask extends AsyncTask<String, Object, Object> {

    MainActivity mainActivity;

    public RestaurantServiceAsyncTask(MainActivity activity) {
        mainActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(String... params) {
        RestaurantService service = new RestaurantService();
        String location = params[0];
        String limit = params[1];
        String lat = params[2];
        String lon = params[3];
        List<Restaurant> restaurantList = new ArrayList<Restaurant>();
        try {
            // restaurantList = service.getRestaurants(location, limit);
            restaurantList = service.getRestaurants(lat, lon, limit);
            return restaurantList;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return restaurantList;
    }

    @Override
    protected void onPostExecute(Object object) {
        super.onPostExecute(object);
        List<Restaurant> restaurantList = (List<Restaurant>) object;
        mainActivity.updateUI(restaurantList);
    }
}
