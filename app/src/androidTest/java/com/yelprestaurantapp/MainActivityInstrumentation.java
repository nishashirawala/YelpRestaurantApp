package com.yelprestaurantapp;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.test.suitebuilder.annotation.Suppress;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.yelprestaurantapp.bean.Restaurant;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.Override;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.TableView;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.core.deps.guava.base.Predicates.equalTo;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MainActivityInstrumentation extends ActivityInstrumentationTestCase2<MainActivity> {



    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    public MainActivityInstrumentation() {
        super(MainActivity.class);

    }

    @Test
    public void testTableViewDisplayed() {
        onView(withId(R.id.tableView)).check(matches(isDisplayed()));
    }

    @Test
    public void testClickOnRestaurant() {
        Restaurant r = new Restaurant();
        r.setId("byblos-toronto-2");
        onData(allOf(is(instanceOf(Restaurant.class)), is(r))).perform(click());
        onView(withId(R.id.detailLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.restaurantImage)).check(matches(isDisplayed()));
        onView(withId(R.id.restaurantName)).check(matches(isDisplayed()));
        onView(withId(R.id.restaurantAddress)).check(matches(isDisplayed()));
        onView(withId(R.id.recommenedReviews)).check(matches(isDisplayed()));
        onView(withId(R.id.userImage)).check(matches(isDisplayed()));
        onView(withId(R.id.userName)).check(matches(isDisplayed()));
        onView(withId(R.id.reviewText)).check(matches(isDisplayed()));
        onView(withId(R.id.overallRatingTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.ratingTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.categoryTextView)).check(matches(isDisplayed()));

    }
}