package com.yelprestaurantapp.yelp;


import com.yelprestaurantapp.TestConstants;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class YelpAPITest {

    private YelpAPI fixture;

    @Before
    public void setup() {
        fixture = new YelpAPI(TestConstants.consumerKey, TestConstants.consumerSecret, TestConstants.token, TestConstants.tokenSecret);
    }

    @Test
    public void testSearchForBusinessesByLocation() throws Exception {
        String response = fixture.search("restaurants", "toronto");
        assertNotNull(response);
    }

    @Test
    public void testGetBusinessDetail() throws Exception {
        String response = fixture.searchBusinessDetail("corrados-toronto");
        assertNotNull(response);
    }
}