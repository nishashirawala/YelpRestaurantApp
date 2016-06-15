package com.yelprestaurantapp;

import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.yelprestaurantapp.bean.Category;
import com.yelprestaurantapp.bean.RestaurantDetail;
import com.yelprestaurantapp.bean.Review;
import com.yelprestaurantapp.bean.Reviewer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DetailActivityTest {

    private DetailActivity fixture;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Intent intent = new Intent();
        intent.putExtra("businessId", "under-the-table-restaurant-toronto");
        fixture = Robolectric.buildActivity(DetailActivity.class).withIntent(intent).create().get();
        Assert.assertNotNull(fixture);
    }


    @Test
    public void onCreate_withNoIntent() throws Exception {
        DetailActivity activity = Robolectric.setupActivity(DetailActivity.class);
        Assert.assertNotNull(activity);
        RelativeLayout layout = (RelativeLayout) activity.findViewById(R.id.detailLayout);
        TextView errorTextView = (TextView) layout.getChildAt(0);
        Assert.assertNotNull(errorTextView);
        String expectedErrorMsg = fixture.getResources().getString(R.string.null_business_id);
        Assert.assertEquals(expectedErrorMsg, errorTextView.getText());
    }


    @Test
    public void testUpdateUI_nullDetails() {
        fixture.setContentView(R.layout.activity_detail);
        fixture.updateUI(null);
        RelativeLayout layout = (RelativeLayout) fixture.findViewById(R.id.detailLayout);
        TextView errorTextView = (TextView) layout.getChildAt(0);
        Assert.assertNotNull(errorTextView);
        String expectedErrorMsg = fixture.getResources().getString(R.string.null_business_detail);
        Assert.assertEquals(expectedErrorMsg, errorTextView.getText());
    }

    @Test
    public void testUpdateUI() {
        fixture.setContentView(R.layout.activity_detail);
        RestaurantDetail detail = mockRestaurantDetail();
        fixture.updateUI(detail);
    }

    private RestaurantDetail mockRestaurantDetail() {
        RestaurantDetail restaurantDetail = new RestaurantDetail();
        restaurantDetail.setId("under-the-table-restaurant-toronto");
        restaurantDetail.setName("Under The Table Restaurant");
        restaurantDetail.setRatingImgUrl("https://s3-media2.fl.yelpcdn.com/assets/2/www/img/99493c12711e/ico/stars/v1/stars_4_half.png");
        restaurantDetail.setRating(4.0);
        restaurantDetail.setImageUrl("https://s3-media3.fl.yelpcdn.com/bphoto/SZGqok34AyExcN_zaZ8MRQ/ms.jpg");

        List<Category> categoryList = Lists.newArrayList();
        Category c1 = new Category();
        c1.setName("Caribbean");
        c1.setAlias("caribbean");
        categoryList.add(c1);
        c1.setName("Comfort Food");
        c1.setAlias("comfortfood");
        categoryList.add(c1);
        c1.setName("Breakfast & Brunch");
        c1.setAlias("breakfast_brunch");
        categoryList.add(c1);

        restaurantDetail.setCategories(categoryList);

        restaurantDetail.setDisplayAddress("568 Parliament St\n" +
                "Cabbagetown\n" +
                "Toronto, ON M4X 1P8\n" +
                "Canada\n");


        Review review = new Review();
        review.setId("4MLX2uWNIa1stbQvvh3p4g");
        review.setRating(5.0);
        review.setRatingImgUrl("https://s3-media1.fl.yelpcdn.com/assets/2/www/img/f1def11e4e79/ico/stars/v1/stars_5.png");
        review.setExcerpt("Hubby and I came to Toronto for my birthday weekend in April. We were looking for Jamician cuisine and found Under The Table in the Yelp App. After reading...");

        Reviewer reviewer = new Reviewer();
        reviewer.setId("meEjJ1HeRexOCRo8GyZ1cg");
        reviewer.setUserName("Viki P.");
        reviewer.setUserImageUrl("http://s3-media4.fl.yelpcdn.com/photo/s0gYYxKvoTsLYqzCHzmTmA/ms.jpg");
        review.setReviewer(reviewer);

        review.setCreatedAt(1465754510L);
        restaurantDetail.setReview(review);

        return restaurantDetail;
    }
}