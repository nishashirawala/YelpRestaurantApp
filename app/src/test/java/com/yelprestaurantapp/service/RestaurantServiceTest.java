package com.yelprestaurantapp.service;


import android.content.Context;

import com.yelprestaurantapp.bean.Restaurant;
import com.yelprestaurantapp.bean.RestaurantDetail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {

    private RestaurantService fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new RestaurantService();
    }

    @Test
    public void testGetRestaurants() {
        List<Restaurant> restaurantList = fixture.getRestaurants("toronto", "10");
        Assert.assertNotNull(restaurantList);
        Assert.assertEquals(10, restaurantList.size());
        for (Restaurant restaurant : restaurantList) {
            Assert.assertNotNull(restaurant.getId());
            Assert.assertNotNull(restaurant.getName());
            Assert.assertNotNull(restaurant.getAddress());
        }
    }

    @Test
    public void testGetRestaurantDetails() {
        RestaurantDetail detail = fixture.getRestaurantDetails("under-the-table-restaurant-toronto");
        Assert.assertNotNull(detail);
        Assert.assertNotNull(detail.getName());
        Assert.assertEquals("Under The Table Restaurant", detail.getName());
        Assert.assertNotNull(detail.getDisplayAddress());
        Assert.assertNotNull(detail.getImageUrl());
        Assert.assertNotNull(detail.getCategories());
    }




}
