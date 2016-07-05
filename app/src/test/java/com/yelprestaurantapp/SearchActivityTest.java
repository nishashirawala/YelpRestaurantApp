package com.yelprestaurantapp;

import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SearchActivityTest {
    private SearchActivity fixture;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        fixture = Robolectric.setupActivity(SearchActivity.class);
    }

    @Test
    public void testSearchRestaurant() {
        EditText searchTxt = (EditText) fixture.findViewById(R.id.searchTxt);
        searchTxt.setText("toronto");

        EditText limitTxt = (EditText) fixture.findViewById(R.id.limitTxt);
        limitTxt.setText("15");

        Button searchBtn = (Button) fixture.findViewById(R.id.searchBtn);
        searchBtn.performClick();

    }


}
