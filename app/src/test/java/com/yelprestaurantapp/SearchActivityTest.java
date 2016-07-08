package com.yelprestaurantapp;

import android.content.Intent;
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
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertTrue;

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

        ShadowActivity shadowActivity = Shadows.shadowOf(fixture);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        Intent expectedIntent = new Intent(fixture, MainActivity.class);
        expectedIntent.putExtra("searchLimit", 15);
        expectedIntent.putExtra("searchLocation", "toronto");
        assertTrue(actualIntent.filterEquals(expectedIntent));
        Assert.assertEquals(expectedIntent.getBundleExtra("searchLimit"), actualIntent.getBundleExtra("searchLimit"));
        Assert.assertEquals(expectedIntent.getBundleExtra("searchLocation"), actualIntent.getBundleExtra("searchLocation"));
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
