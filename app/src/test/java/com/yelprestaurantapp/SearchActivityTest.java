package com.yelprestaurantapp;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Assert;
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


    @Test
    public void testSearchRestaurant_showError() {
        Button searchBtn = (Button) fixture.findViewById(R.id.searchBtn);
        searchBtn.performClick();
        LinearLayout searchLayout = (LinearLayout) fixture.findViewById(R.id.searchLayout);
        TextView errorTextView = (TextView) searchLayout.getChildAt(0);
        Assert.assertNotNull(errorTextView);
        String expectedErrorMsg = fixture.getResources().getString(R.string.empty_search_text);
        Assert.assertEquals(expectedErrorMsg, errorTextView.getText());
    }


}
