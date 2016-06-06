package com.yelprestaurantapp.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.yelprestaurantapp.bean.Restaurant;
import com.yelprestaurantapp.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

public class RestaurantServiceAsyncTask extends AsyncTask<String, Object, List<Restaurant>> {

    ResultListener resultListener;

    public interface ResultListener {
        void handleAsyncResult(List<Restaurant> restaurantList);
    }

    public RestaurantServiceAsyncTask(ResultListener listener) {
        this.resultListener = listener;
    }

    @Override
    protected List<Restaurant> doInBackground(String... params) {
        // TODO : Look for dependecny injection or service locator to get instance of the service.
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
    protected void onPostExecute(List<Restaurant> restaurantList) {
        super.onPostExecute(restaurantList);
        resultListener.handleAsyncResult(restaurantList);
    }
}
