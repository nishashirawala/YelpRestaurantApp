package com.yelprestaurantapp;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.test.ActivityTestCase;
import android.test.mock.MockContext;
import android.test.mock.MockResources;

import com.yelprestaurantapp.bean.Restaurant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.SortableTableView;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    private MainActivity fixture;
    private Context mockContext;
    private Resources mockResources;
    private ContextCompat contextCompat;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockContext = mock(Context.class);
        // mockResources = mock(Resources.class);
        // contextCompat = mock(ContextCompat.class);
        fixture = new MainActivity();
    }

    @Test
    public void testUpdateUI() {
        SortableTableView<Restaurant> tableView = mock(SortableTableView.class);
        Mockito.doReturn(tableView).when(fixture).findViewById(R.id.tableView);

        when(mockContext.getResources()).thenReturn(mockResources);
        when(mockResources.getString(R.string.empty_restaurant_list)).thenReturn("aa");
        /*when(mockContext.getString(R.string.nameHeader)).thenReturn("Name");
        when(mockContext.getString(R.string.addressHeader)).thenReturn("Address");
        when(mockContext.getColor(R.color.white)).thenReturn(R.color.white);
        when(mockContext.getColor(R.color.ltgray)).thenReturn(R.color.ltgray);*/

        /*when(mockContext.getResources()).thenReturn(mockResources);
        when(mockResources.getString(R.string.empty_restaurant_list)).thenReturn("aa");
*/
        List<Restaurant> list = new ArrayList<>();
        list.add(new Restaurant());
        fixture.updateUI(list);
    }

    public Resources getMockResources() {
        Resources res = new MockResources() {
            @Override
            public int getColor(int id) throws NotFoundException {
                if(id == R.color.white) {
                    return 1;
                }
                return 0;
            }

            @Override
            public String getString(int id) throws NotFoundException {
                if(id == R.string.nameHeader) {
                    return  "Name";
                }
                return "not found";
            }
        };

        return res;
    }

}
