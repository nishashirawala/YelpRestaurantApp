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
    }
}
