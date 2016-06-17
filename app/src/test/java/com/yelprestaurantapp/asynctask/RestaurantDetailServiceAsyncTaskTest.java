package com.yelprestaurantapp.asynctask;


import com.yelprestaurantapp.BuildConfig;
import com.yelprestaurantapp.bean.RestaurantDetail;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RestaurantDetailServiceAsyncTaskTest implements RestaurantDetailServiceAsyncTask.ResultListener{

    private RestaurantDetailServiceAsyncTask fixture;
    private RestaurantDetail asyncResult;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        fixture = new RestaurantDetailServiceAsyncTask(this);
        Assert.assertNotNull(fixture);
    }

    @Test
    public void testExecute() {
        String id = "under-the-table-restaurant-toronto";
        String[] params = {id};
        fixture.execute(params);
        Assert.assertNotNull(asyncResult);

        Assert.assertEquals("Under The Table Restaurant", asyncResult.getName());
        Assert.assertEquals(3, asyncResult.getCategories().size());
    }
    @Override
    public void handleAsyncResult(RestaurantDetail restaurantDetail) {
        asyncResult = restaurantDetail;
    }
}
