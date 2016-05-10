package com.yelprestaurantapp.service;


import android.content.Context;

import com.yelprestaurantapp.bean.Category;
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
        List<Restaurant> restaurantList = fixture.getRestaurants("Financial District, toronto", "10");
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
        Assert.assertNotNull(detail.getRating());
        Assert.assertNotNull(detail.getRatingImgUrl());
    }


    @Test
    public void testSearchForBusinessesByLatLon() {
        List<Restaurant> restaurantList = fixture.getRestaurants("43.6611", "-79.42868", "20");
        Assert.assertNotNull(restaurantList);
        for (Restaurant restaurant : restaurantList) {
            Assert.assertNotNull(restaurant.getId());
            Assert.assertNotNull(restaurant.getName());
            Assert.assertNotNull(restaurant.getAddress());
        }
    }


    @Test
    public void testGetRestaurants_byCategory() {
        RestaurantDetail detail = fixture.getRestaurantDetails("nazareth-restaurant-toronto");
        Assert.assertNotNull(detail);
        Assert.assertNotNull(detail.getCategories());
        Assert.assertEquals("Nazareth Restaurant", detail.getName());
        Assert.assertEquals(2, detail.getCategories().size());
        Category c1 =  detail.getCategories().get(0);
        Category c2 =  detail.getCategories().get(1);
        Assert.assertEquals("Ethiopian", c1.getName());
        Assert.assertEquals("Vegetarian", c2.getName());
    }
}
