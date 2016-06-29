package com.yelprestaurantapp;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class DetailActivityInstrumentation extends ActivityInstrumentationTestCase2<DetailActivity> {

    private Context mContext;

    public DetailActivityInstrumentation() {
        super(DetailActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mContext = getInstrumentation().getContext();
        super.setUp();
    }

    @Test
    public void testDetailActivityLaunched() {
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra("businessId", "the-gabardine-toronto");
        launchActivityWithIntent("com.yelprestaurantapp", DetailActivity.class, intent);
        onView(allOf(withId(R.id.detailLayout))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.restaurantName))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.restaurantAddress))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.recommenedReviews))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.userImage))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.userName))).check(matches(isDisplayed()));
    }
}
