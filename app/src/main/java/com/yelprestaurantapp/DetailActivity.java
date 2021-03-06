package com.yelprestaurantapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yelprestaurantapp.asynctask.RestaurantDetailServiceAsyncTask;
import com.yelprestaurantapp.bean.Category;
import com.yelprestaurantapp.bean.RestaurantDetail;
import com.yelprestaurantapp.bean.Review;
import com.yelprestaurantapp.bean.Reviewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class DetailActivity extends AppCompatActivity implements RestaurantDetailServiceAsyncTask.ResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String businessID = intent.getStringExtra("businessId");
        if (businessID != null) {
            String[] params = {businessID};
            new RestaurantDetailServiceAsyncTask(this).execute(params);
        } else {
            showErrorMsg(getString(R.string.null_business_id));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateUI(RestaurantDetail detail) {
        if (detail != null) {
            TextView resNameTextView = (TextView) findViewById(R.id.restaurantName);
            resNameTextView.setText(detail.getName());
            List<Category> categoryList = detail.getCategories();
            String categoryStr = createCategoryString(categoryList);
            TextView catTextView = (TextView) findViewById(R.id.categoryTextView);
            catTextView.setText(categoryStr);
            TextView addressTextView = (TextView) findViewById(R.id.restaurantAddress);
            addressTextView.setText(detail.getDisplayAddress());
            new DownloadHttpsImageTask((ImageView) findViewById(R.id.restaurantImage)).execute(detail.getImageUrl());
            TextView ratingTextView = (TextView) findViewById(R.id.ratingTextView);
            ratingTextView.setText(String.valueOf(detail.getRating()));
            Review review = detail.getReview();
            TextView reviewText = (TextView) findViewById(R.id.reviewText);
            reviewText.setText(review.getExcerpt());
            TextView userName = (TextView) findViewById(R.id.userName);
            Reviewer reviewer = review.getReviewer();
            userName.setText(reviewer.getUserName());
            String userImgUrl = detail.getReview().getReviewer().getUserImageUrl();
            if (userImgUrl.startsWith("http://")) {
                new DownloadImageTask((ImageView) findViewById(R.id.userImage)).execute(userImgUrl);
            } else {
                new DownloadHttpsImageTask((ImageView) findViewById(R.id.userImage)).execute(userImgUrl);
            }
        } else {
            showErrorMsg(getString(R.string.null_business_detail));
        }
    }

    @NonNull
    private String createCategoryString(List<Category> categoryList) {
        String categoryStr = "";
        if (categoryList != null && categoryList.size() > 0) {
            for (int i = 0; i < categoryList.size(); i++) {
                Category c = categoryList.get(i);
                if (i == categoryList.size() - 1) {
                    categoryStr += c.getName();
                } else {
                    categoryStr += c.getName() + " | ";
                }
            }
        }
        return categoryStr;
    }

    @Override
    public void handleAsyncResult(RestaurantDetail restaurantDetail) {
        this.updateUI(restaurantDetail);
    }

    private class DownloadHttpsImageTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView bmImage;

        public DownloadHttpsImageTask(ImageView bmImage) {
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

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView bmImage;

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

    private void showErrorMsg(String errorMsg) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.detailLayout);
        layout.removeAllViews();
        TextView errorTextView = new TextView(this);
        errorTextView.setTextColor(Color.RED);
        errorTextView.setText(errorMsg);
        errorTextView.setPadding(10, 10, 10, 10);
        layout.addView(errorTextView);
        Log.e("Error", errorMsg);
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
            if (stream != null) {
                stream.close();
            }
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
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

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
