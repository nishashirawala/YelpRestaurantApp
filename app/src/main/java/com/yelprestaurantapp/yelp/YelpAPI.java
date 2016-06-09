package com.yelprestaurantapp.yelp;

import android.content.Context;

import com.yelprestaurantapp.R;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class YelpAPI {

    private static String SEARCH_URL = "http://api.yelp.com/v2/search";
    private static String BUSINESS_URL = "http://api.yelp.com/v2/business";
    OAuthService service;
    Token accessToken;

    public static String consumerKey = "IMv6Cs7XEvqy8iT7XcPKcA";
    public static String consumerSecret = "qoRGwOGWrx34CmV3wDFp3hDaJNk";
    public static String token = "ZXmuLpTiMlTrhtV90fmRBfuyBjtWT4W_";
    public static String tokenSecret = "Ci-K4yUAoEPdQpEbWBsmE5ulWJI";

    public static YelpAPI getYelp() {
        return new YelpAPI(consumerKey, consumerSecret, token, tokenSecret);
    }

    public YelpAPI(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.service = new ServiceBuilder().provider(YelpApiOAuth.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
        this.accessToken = new Token(token, tokenSecret);
    }

    public String searchForBusinessesByLocation(String term, String location, String limit) {
        OAuthRequest request = new OAuthRequest(Verb.GET, SEARCH_URL);
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", limit);
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    public String searchBusinessDetail(String businessId) {
        OAuthRequest request = new OAuthRequest(Verb.GET, BUSINESS_URL + "/" + businessId);
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    public String searchForBusinessesByLatLon(String lat, String lon, String term, String limit) {
        OAuthRequest request = new OAuthRequest(Verb.GET, SEARCH_URL);
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("ll", lat + "," + lon);
        request.addQuerystringParameter("limit", limit);
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }
}