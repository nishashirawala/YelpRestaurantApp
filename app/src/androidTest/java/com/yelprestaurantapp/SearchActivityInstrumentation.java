package com.yelprestaurantapp;


import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.EditText;

import com.yelprestaurantapp.bean.Restaurant;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SearchActivityInstrumentation extends ActivityInstrumentationTestCase2<SearchActivity> {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(SearchActivity.class);

    private Activity mActivity = null;

    public SearchActivityInstrumentation() {
        super(SearchActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        setActivityInitialTouchMode(false);
        mActivity = mActivityRule.getActivity();
    }


    @Test
    public void testActivityLaunched() {
        onView(allOf(withId(R.id.searchTxt))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.searchBtn))).check(matches(isDisplayed()));
    }

    @Test
    public void testSearchRestaurant() {
        onView(allOf(withId(R.id.searchTxt))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.limitTxt))).check(matches(isDisplayed()));
        final EditText searchTxt = (EditText) mActivity.findViewById(R.id.searchTxt);
        final EditText limitTxt = (EditText) mActivity.findViewById(R.id.limitTxt);
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                searchTxt.requestFocus();
                searchTxt.setText("ottawa");
                limitTxt.requestFocus();
                limitTxt.setText("15");
            }
        });
        onView(withId(R.id.searchBtn)).perform(click());
        onView(allOf(withId(R.id.tableView))).check(matches(isDisplayed()));
        onData(anyOf(is(instanceOf(Restaurant.class)))).atPosition(0).perform(click());
        verifyDetailViewElementsDisplayed();
    }

    private void verifyDetailViewElementsDisplayed() {
        onView(allOf(withId(R.id.detailLayout))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.restaurantName))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.restaurantAddress))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.recommenedReviews))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.userImage))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.userName))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.reviewText))).check(matches(isDisplayed()));
    }
}
