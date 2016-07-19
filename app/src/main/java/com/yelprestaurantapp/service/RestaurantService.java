package com.yelprestaurantapp.service;


import com.yelprestaurantapp.bean.Category;
import com.yelprestaurantapp.bean.Restaurant;
import com.yelprestaurantapp.bean.RestaurantDetail;
import com.yelprestaurantapp.bean.Review;
import com.yelprestaurantapp.bean.Reviewer;
import com.yelprestaurantapp.yelp.YelpAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RestaurantService {

    private static String term = "restaurant";

    public List<Restaurant> getRestaurants(String location, String limit) {
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        YelpAPI yelp = YelpAPI.getYelp();
        String response = yelp.searchForBusinessesByLocation(term, location, limit);
        try {
            createRestaurantListFromJSON(restaurants, response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    public List<Restaurant> getRestaurants(String lat, String lon, String limit) throws Exception {
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        YelpAPI yelp = YelpAPI.getYelp();
        String response = yelp.searchForBusinessesByLatLon(lat, lon, term, limit);
        try {
            createRestaurantListFromJSON(restaurants, response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    private void createRestaurantListFromJSON(List<Restaurant> restaurants, String response) throws JSONException {
        JSONObject json = new JSONObject(response);
        JSONArray businesses = json.getJSONArray("businesses");
        for (int i = 0; i < businesses.length(); i++) {
            JSONObject business = businesses.getJSONObject(i);
            Restaurant res = new Restaurant();
            res.setId(business.getString("id"));
            res.setName(business.getString("name"));
            JSONObject addressJsonObj = business.getJSONObject("location");
            JSONArray addresses = addressJsonObj.getJSONArray("address");
            if (addresses != null && addresses.length() > 0) {
                res.setAddress(addresses.getString(0));
            } else {
                res.setAddress("Not Available");
            }
            restaurants.add(res);
        }
    }


    public RestaurantDetail getRestaurantDetails(String businessId) {
        YelpAPI yelp = YelpAPI.getYelp();
        String response = yelp.searchBusinessDetail(businessId);
        RestaurantDetail restaurantDetail = new RestaurantDetail();
        try {

            JSONObject business = new JSONObject(response);
            restaurantDetail.setId(business.getString("id"));
            restaurantDetail.setName(business.getString("name"));
            restaurantDetail.setImageUrl(business.getString("image_url"));
            JSONObject addressJsonObj = business.getJSONObject("location");
            JSONArray displayAddresses = addressJsonObj.getJSONArray("display_address");
            String displayAddress = "";
            for (int i = 0; i < displayAddresses.length(); i++) {
                displayAddress += displayAddresses.getString(i) + "\n";
            }
            restaurantDetail.setDisplayAddress(displayAddress);
            JSONArray reviewJsonArray = business.getJSONArray("reviews");
            JSONObject reviewJsonObj = reviewJsonArray.getJSONObject(0);

            Review review = new Review();
            review.setExcerpt(reviewJsonObj.getString("excerpt"));

            JSONObject userObj = reviewJsonObj.getJSONObject("user");
            Reviewer reviewer = new Reviewer();
            reviewer.setId(userObj.getString("id"));
            reviewer.setUserName(userObj.getString("name"));
            reviewer.setUserImageUrl(userObj.getString("image_url"));
            review.setReviewer(reviewer);

            restaurantDetail.setReview(review);

            JSONArray categoryJsonArray = business.getJSONArray("categories");
            List<Category> categories = new ArrayList<>();
            for (int i = 0; i < categoryJsonArray.length(); i++) {
                JSONArray catJsonArr = (JSONArray) categoryJsonArray.get(i);
                Category category = new Category();
                category.setName(catJsonArr.getString(0));
                category.setAlias(catJsonArr.getString(1));
                categories.add(category);
            }
            restaurantDetail.setCategories(categories);

            restaurantDetail.setRating(Double.valueOf(business.getInt("rating")));
            restaurantDetail.setRatingImgUrl(business.getString("rating_img_url"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurantDetail;
    }

}
