package com.yelprestaurantapp.bean;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantTest {

    private Restaurant fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new Restaurant();
        fixture.setId("abcd");
    }

    @Test
    public void testEquals() {
        Restaurant r = new Restaurant();
        r.setId("abcd");
        Assert.assertTrue(fixture.equals(r));
    }

    @Test
    public void testNotEquals() {
        Restaurant r = new Restaurant();
        r.setId("eeee");
        Assert.assertFalse(fixture.equals(r));
    }

}
