package com.yelprestaurantapp.yelp;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class YelpAPITest {

    private YelpAPI fixture;

    @Before
    public void setUp() {
        fixture = YelpAPI.getYelp();
    }

    @Test
    public void testSearchForBusinessesByLocation() throws Exception {
        String response = fixture.searchForBusinessesByLocation("restaurants", "toronto", "10");
        assertNotNull(response);
    }

    @Test
    public void testGetBusinessDetail() throws Exception {
        String response = fixture.searchBusinessDetail("corrados-toronto");
        assertNotNull(response);
    }

    @Test
    public void testSearchForBusinessesByLatLon() throws Exception {
        String response = fixture.searchForBusinessesByLatLon("43.648742", "-79.387199", "restaurants", "10");
        assertNotNull(response);
    }
}