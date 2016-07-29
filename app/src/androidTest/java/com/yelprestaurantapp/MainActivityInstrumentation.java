package com.yelprestaurantapp;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.yelprestaurantapp.bean.Restaurant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MainActivityInstrumentation extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityInstrumentation() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        Context mContext = getInstrumentation().getContext();
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.putExtra("searchLocation", "toronto");
        intent.putExtra("searchLimit", "20");
        launchActivityWithIntent("com.yelprestaurantapp", MainActivity.class, intent);
        super.setUp();
    }

    @Test
    public void testTableViewDisplayed() {
        onView(withId(R.id.tableView)).check(matches(isDisplayed()));
    }

    @Test
    public void testClickOnRestaurantsByPosition() {
        onData(anyOf(is(instanceOf(Restaurant.class)))).atPosition(0).perform(click());
        verifyDetailViewElementsDisplayed();
        pressBack();
        onData(anyOf(is(instanceOf(Restaurant.class)))).atPosition(1).perform(click());
        verifyDetailViewElementsDisplayed();
    }

    @Test
    public void testClickOnRestaurantByName() {
        Restaurant r = new Restaurant();
        r.setId("under-the-table-restaurant-toronto");
        onData(allOf(is(instanceOf(Restaurant.class)), is(r))).perform(click());
        verifyDetailViewElementsDisplayed();
        onView(allOf(withId(R.id.restaurantName))).check(matches(withText("Under The Table Restaurant")));
    }

    private void verifyDetailViewElementsDisplayed() {
        onView(withId(R.id.detailLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.restaurantName)).check(matches(isDisplayed()));
        onView(withId(R.id.restaurantAddress)).check(matches(isDisplayed()));
        onView(withId(R.id.recommenedReviews)).check(matches(isDisplayed()));
        onView(withId(R.id.userImage)).check(matches(isDisplayed()));
        onView(withId(R.id.userName)).check(matches(isDisplayed()));
        onView(withId(R.id.reviewText)).check(matches(isDisplayed()));
    }
}