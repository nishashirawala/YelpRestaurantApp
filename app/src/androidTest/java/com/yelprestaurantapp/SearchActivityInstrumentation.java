package com.yelprestaurantapp;


import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SearchActivityInstrumentation extends ActivityInstrumentationTestCase2<SearchActivity> {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    public SearchActivityInstrumentation() {
        super(SearchActivity.class);
    }

    @Test
    public void testActivityLaunched() {
        testActivityLaunched();
    }


}
