package com.yelprestaurantapp.listener;

import android.content.Context;
import android.content.Intent;

import com.yelprestaurantapp.BuildConfig;
import com.yelprestaurantapp.DetailActivity;
import com.yelprestaurantapp.MainActivity;
import com.yelprestaurantapp.bean.Restaurant;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RestaurantDataClickListenerTest {

    private RestaurantDataClickListener fixture;
    private MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Context context = RuntimeEnvironment.application.getApplicationContext();
        fixture = new RestaurantDataClickListener(context);
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void testOnDataClicked() {
        Restaurant mockRestaurant = mockRestaurant("r1", "r1-name", "r1-address");
        fixture.onDataClicked(0, mockRestaurant);

        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        Intent expectedIntent = new Intent(mainActivity, DetailActivity.class);
        expectedIntent.putExtra("businessId", mockRestaurant.getId());
        assertTrue(actualIntent.filterEquals(expectedIntent));
        Assert.assertEquals(expectedIntent.getBundleExtra("businessId"), actualIntent.getBundleExtra("businessId"));
    }

    private Restaurant mockRestaurant(String id, String name, String address) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        restaurant.setName(name);
        restaurant.setAddress(address);

        return restaurant;
    }
}
