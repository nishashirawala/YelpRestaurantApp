package com.yelprestaurantapp.asynctask;


import android.content.Context;
import android.os.AsyncTask;

import com.yelprestaurantapp.BuildConfig;
import com.yelprestaurantapp.MainActivity;
import com.yelprestaurantapp.R;
import com.yelprestaurantapp.bean.Restaurant;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RestaurantServiceAsyncTaskTest implements RestaurantServiceAsyncTask.ResultListener{

    private RestaurantServiceAsyncTask fixture;
    private MainActivity mockMainActivity;
    private List<Restaurant> asyncResult;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMainActivity = mock(MainActivity.class);
        Assert.assertNotNull(mockMainActivity);
        fixture = new RestaurantServiceAsyncTask(mockMainActivity, this);
        Assert.assertNotNull(fixture);
    }

    @Test
    public void testExecute() {

        String location = "toronto";
        String limit = "10";
        String lat = "43.648742";
        String lon = "-79.387199";

        String[] params = {location, limit, lat, lon};
        AsyncTask<String, Object, List<Restaurant>> task = fixture.execute(params);
        List<Restaurant> list = new ArrayList< Restaurant >();
        doNothing().when(mockMainActivity).updateUI(list);
        Assert.assertNotNull(asyncResult);
    }


    @Override
    public void handleAsyncResult(List<Restaurant> restaurantList) {
        asyncResult = restaurantList;
    }
}
