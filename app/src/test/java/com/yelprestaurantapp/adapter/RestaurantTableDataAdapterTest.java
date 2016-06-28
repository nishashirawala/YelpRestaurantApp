package com.yelprestaurantapp.adapter;

import android.content.Context;

import com.google.common.collect.Lists;
import com.yelprestaurantapp.BuildConfig;
import com.yelprestaurantapp.MainActivity;
import com.yelprestaurantapp.bean.Restaurant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.mockito.Mockito.mock;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RestaurantTableDataAdapterTest {

    private Context mockContext;
    private MainActivity mockMainActivity;
    private RestaurantTableDataAdapter fixture;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockContext = mock(Context.class);
        mockMainActivity = Robolectric.buildActivity(MainActivity.class).get();
        fixture = new RestaurantTableDataAdapter(mockContext, mockRestaurantList());
    }

    @Test
    public void testGetCellView() throws Exception {
        // fixture.getCellView(0, 0, null);
    }


    private List<Restaurant> mockRestaurantList() {
        List<Restaurant> list = Lists.newArrayList();

        list.add(mockRestaurant("r3", "C3-name", "r3-address"));
        list.add(mockRestaurant("r2", "B2-name", "r2-address"));
        list.add(mockRestaurant("r1", "A1-name", "r1-address"));
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
