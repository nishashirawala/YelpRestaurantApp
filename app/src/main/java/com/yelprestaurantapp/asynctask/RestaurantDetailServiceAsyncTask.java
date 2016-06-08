package com.yelprestaurantapp.asynctask;

import android.os.AsyncTask;

import com.yelprestaurantapp.bean.RestaurantDetail;
import com.yelprestaurantapp.service.RestaurantService;

public class RestaurantDetailServiceAsyncTask extends AsyncTask<String, Object, RestaurantDetail> {

    ResultListener resultListener;

    public interface ResultListener {
        void handleAsyncResult(RestaurantDetail restaurantDetail);
    }


    public RestaurantDetailServiceAsyncTask(ResultListener listener) {
        this.resultListener = listener;
    }

    @Override
    protected RestaurantDetail doInBackground(String[] params) {
        RestaurantService service = new RestaurantService();
        RestaurantDetail restaurantDetails = service.getRestaurantDetails(params[0]);
        return restaurantDetails;
    }

    @Override
    protected void onPostExecute(RestaurantDetail restaurantDetail) {
        super.onPostExecute(restaurantDetail);
        resultListener.handleAsyncResult(restaurantDetail);
    }
}
