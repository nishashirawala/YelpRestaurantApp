package com.yelprestaurantapp.adapter;

import android.view.View;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.yelprestaurantapp.BuildConfig;
import com.yelprestaurantapp.MainActivity;
import com.yelprestaurantapp.bean.Restaurant;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RestaurantTableDataAdapterTest {

    private MainActivity mockMainActivity;
    private RestaurantTableDataAdapter fixture;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMainActivity = Robolectric.setupActivity(MainActivity.class);
        fixture = new RestaurantTableDataAdapter(mockMainActivity, mockRestaurantList());
    }

    @Test
    public void testGetCellView() throws Exception {
        TextView restaurantName = (TextView) fixture.getCellView(0, 0, null);
        Assert.assertEquals("A1-name", restaurantName.getText());
        TextView restaurantAddress = (TextView) fixture.getCellView(0, 1, null);
        Assert.assertEquals("r1-address", restaurantAddress.getText());
        View view = fixture.getCellView(1, 1, null);
        Assert.assertNull(view);
    }


    private List<Restaurant> mockRestaurantList() {
        List<Restaurant> list = Lists.newArrayList();

        list.add(mockRestaurant("r1", "A1-name", "r1-address"));
        list.add(mockRestaurant("r2", "B2-name", "r2-address"));
        list.add(mockRestaurant("r3", "C3-name", "r3-address"));
        list.add(mockRestaurant("r4", "D4-name", "r4-address"));

        return list;
    }

    private Restaurant mockRestaurant(String id, String name, String address) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        restaurant.setName(name);
        restaurant.setAddress(address);

        return restaurant;
    }
}
