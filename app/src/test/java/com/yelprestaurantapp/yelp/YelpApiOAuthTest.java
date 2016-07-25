package com.yelprestaurantapp.yelp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class YelpApiOAuthTest {

    private YelpApiOAuth fixture;

    @Before
    public void setUp() {
        fixture = new YelpApiOAuth();
    }

    @Test
    public void testGetAccessTokenEndpoint() {
        assertNull(fixture.getAccessTokenEndpoint());
    }

    @Test
    public void testGetAuthorizationUrl() {
        assertNull(fixture.getAuthorizationUrl(null));
    }

    @Test
    public void testGetRequestTokenEndpoint() {
        assertNull(fixture.getRequestTokenEndpoint());
    }
}
