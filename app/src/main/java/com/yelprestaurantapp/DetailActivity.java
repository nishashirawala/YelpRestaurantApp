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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
                .execute(detail.getReview().getReviewer().getUserImageUrl());
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            return downloadImage(urlDisplay);
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

    private Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        InputStream stream = null;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = 1;

        try {
            stream = getHttpConnection(url);
            bitmap = BitmapFactory.
                    decodeStream(stream, null, bmOptions);
            stream.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return bitmap;
    }

    private InputStream getHttpConnection(String urlString) throws IOException {
        InputStream stream = null;
        URL url = new URL(urlString);

        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");

            ctx.init(null, new TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            }, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
            URLConnection connection = url.openConnection();
            HttpsURLConnection httpConnection = (HttpsURLConnection) connection;
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            if (httpConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                stream = httpConnection.getInputStream();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stream;
    }

}
