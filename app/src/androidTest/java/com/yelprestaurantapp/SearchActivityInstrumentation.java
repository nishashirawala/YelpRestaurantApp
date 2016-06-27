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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SearchActivityInstrumentation extends ActivityInstrumentationTestCase2<SearchActivity> {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(SearchActivity.class);

    public SearchActivityInstrumentation() {
        super(SearchActivity.class);
    }

    @Test
    public void testActivityLaunched() {
        onView(withId(R.id.searchTxt)).check(matches(isDisplayed()));
        onView(withId(R.id.searchBtn)).check(matches(isDisplayed()));
    }


}
