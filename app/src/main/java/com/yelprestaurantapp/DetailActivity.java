package com.yelprestaurantapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.yelprestaurantapp.bean.RestaurantDetail;
import com.yelprestaurantapp.bean.Review;
import com.yelprestaurantapp.bean.Reviewer;
import com.yelprestaurantapp.service.RestaurantService;

import java.io.InputStream;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String businessID = intent.getStringExtra("businessId");

        new RestaurantDetailServiceAsyncTask(businessID).execute();
    }

    public void updateUI(RestaurantDetail detail) {
        TextView resNameTextView = (TextView) findViewById(R.id.restaurantName);
        resNameTextView.setText(detail.getName());

        TextView addressTextView = (TextView) findViewById(R.id.restaurantAddress);
        addressTextView.setText(detail.getDisplayAddress());

        new DownloadImageTask((ImageView) findViewById(R.id.restaurantImage))
                .execute(detail.getImageUrl());

        Review review = detail.getReview();
        TextView reviewText = (TextView) findViewById(R.id.reviewText);
        reviewText.setText(review.getExcerpt());

        TextView userName = (TextView) findViewById(R.id.userName);
        Reviewer reviewer = review.getReviewer();
        userName.setText(reviewer.getUserName());

        new DownloadImageTask((ImageView) findViewById(R.id.userImage))
                .execute( detail.getReview().getReviewer().getUserImageUrl());
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    private class RestaurantDetailServiceAsyncTask extends AsyncTask {

        String businessId;

        public RestaurantDetailServiceAsyncTask(String businssId) {
            this.businessId = businssId;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            RestaurantService service = new RestaurantService();
            RestaurantDetail restaurantDetails = service.getRestaurantDetails(businessId);
            return restaurantDetails;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            RestaurantDetail restaurantDetail = (RestaurantDetail) o;
            DetailActivity.this.updateUI(restaurantDetail);
        }
    }
}
