package com.yelprestaurantapp;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.test.suitebuilder.annotation.Suppress;
import android.view.View;

import com.yelprestaurantapp.bean.Restaurant;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.Override;

import de.codecrafters.tableview.SortableTableView;

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

    @Suppress
    public void testFirstPage() {
        onView(withId(R.id.tableView)).check(matches(isDisplayed()));
      /*  onData(hasToString(startsWith("Byblos")))
                .inAdapterView(withId(R.id.tableView))
                .perform(click());*/

        //onData(hasEntry(equalTo("STR"), is("Byblos")));
        // onData(hasEntry(equalTo("STR"), is("aa")));
        // onData(withItemContent("Byblos")).perform(click());
        // onData(instanceOf(String.class)).inAdapterView(withText("Byblos")).atPosition(3).perform(click());
        // onData(hasToString(startsWith("Byblos"))).inAdapterView(withContentDescription("Byblos")).check(matches(isDisplayed()));
        //onData(hasToString(startsWith("Byblos"))).inAdapterView(withContentDescription("Byblos")).check(matches(isClickable()));
        // onData(hasToString(startsWith("Byblos"))).inAdapterView(withContentDescription("Byblos")).perform(click());
       //  onData(hasToString(startsWith("Byblos"))).inAdapterView(withContentDescription("Byblos")).atPosition(4).perform(click());
        // onView(withText("Pearl Driver")).perform(click()).check(matches(isDisplayed()));
    }
}