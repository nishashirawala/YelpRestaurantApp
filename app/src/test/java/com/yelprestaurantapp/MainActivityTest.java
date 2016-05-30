package com.yelprestaurantapp;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.test.mock.MockResources;

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

import de.codecrafters.tableview.SortableTableView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    private MainActivity fixture;
    private Context mockContext;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockContext = mock(Context.class);
        fixture = Robolectric.setupActivity(MainActivity.class);
        Assert.assertNotNull(fixture);
    }

    @Test
    public void testUpdateUI() {
        SortableTableView<Restaurant> tableView = (SortableTableView) fixture.findViewById(R.id.tableView);
        Assert.assertNotNull(tableView);
        List<Restaurant> list = new ArrayList<>();
        list.add(new Restaurant());
        fixture.updateUI(list);
    }
}
