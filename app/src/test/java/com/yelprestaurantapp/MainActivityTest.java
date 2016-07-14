package com.yelprestaurantapp;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.yelprestaurantapp.bean.Restaurant;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.SortableTableView;

import static com.yelprestaurantapp.MainActivity.RestaurantNameComparator;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    private MainActivity fixture;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Intent intent = new Intent();
        intent.putExtra("searchLocation", "toronto");
        intent.putExtra("searchLimit", "10");
        fixture = Robolectric.buildActivity(MainActivity.class).withIntent(intent).create().get();
        Assert.assertNotNull(fixture);
    }

    @Test
    public void testUpdateUI() {
        SortableTableView<Restaurant> tableView = (SortableTableView) fixture.findViewById(R.id.tableView);
        Assert.assertNotNull(tableView);
        List<Restaurant> mockList = mockRestaurantList();
        fixture.updateUI(mockList);
        List<Restaurant> data = tableView.getDataAdapter().getData();
        Assert.assertNotNull(data);
        Assert.assertEquals(mockList.size(), data.size());
        Assert.assertEquals("C3-name", data.get(0).getName());
        Assert.assertEquals("r3", data.get(0).getId());
        Assert.assertEquals("r3-address", data.get(0).getAddress());
    }

    @Test
    public void testUpdateUI_withEmptyList() {
        SortableTableView<Restaurant> tableView = (SortableTableView) fixture.findViewById(R.id.tableView);
        Assert.assertNotNull(tableView);
        List<Restaurant> mockList = new ArrayList<>();
        fixture.updateUI(mockList);
        TextView errorTextView = (TextView) tableView.getChildAt(0);
        Assert.assertNotNull(errorTextView);
        String expectedErrorMsg = fixture.getResources().getString(R.string.empty_restaurant_list);
        Assert.assertEquals(expectedErrorMsg, errorTextView.getText());
    }

    @Test
    public void testRestaurantNameComparator() {
        SortableTableView<Restaurant> tableView = (SortableTableView) fixture.findViewById(R.id.tableView);
        RestaurantNameComparator restaurantNameComparator = (RestaurantNameComparator) tableView.getColumnComparator(0);
        Assert.assertNotNull(restaurantNameComparator);
        List<Restaurant> mockList = mockRestaurantList();
        Assert.assertEquals(1, restaurantNameComparator.compare(mockList.get(0), mockList.get(1)));
        Assert.assertEquals(-1, restaurantNameComparator.compare(mockList.get(1), mockList.get(0)));
        Assert.assertEquals(1, restaurantNameComparator.compare(mockList.get(1), mockList.get(2)));
    }

    @Test
    public void testOnOptionsItemSelected() {
        MenuItem menuItem = new RoboMenuItem(R.id.home);
        boolean returnVal = fixture.onOptionsItemSelected(menuItem);
        Assert.assertFalse(returnVal);
        menuItem = new RoboMenuItem(android.R.id.home);
        returnVal = fixture.onOptionsItemSelected(menuItem);
        Assert.assertTrue(returnVal);
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
