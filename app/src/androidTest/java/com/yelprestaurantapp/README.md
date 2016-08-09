<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [Android Testing Library](#android-testing-library)
- [Instrument Tests Using Espresso](#instrument-tests-using-espresso)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# Android Testing Library
- Android testing support library provides various APIs that allow you to quickly build and run test code for your apps, including JUnit 4 and functional UI tests. The library includes the following instrumentation-based APIs that are useful when you want to automate your tests:
- [AndroidJUnitRunner] (https://developer.android.com/topic/libraries/testing-support-library/index.html#AndroidJUnitRunner)
   A JUnit 4-compatible test runner for Android.
- [Espresso] (https://developer.android.com/topic/libraries/testing-support-library/index.html#Espresso)
   A UI testing framework; suitable for functional UI testing within an app.
- [UI Automator](https://developer.android.com/topic/libraries/testing-support-library/index.html#UIAutomator)
   A UI testing framework suitable for cross-app functional UI testing between both system and installed apps.

# Instrument Tests Using Espresso
- Write basic test as Junit4 test class and then use Espresso API to perform events on UI element.
- Espresso API works with android 2.2 (api level 8) or higher
- Key features
 1. [View Matching] (https://developer.android.com/topic/libraries/testing-support-library/index.html#espresso-matching)
     - [Espresso.onView()](https://developer.android.com/reference/android/support/test/espresso/Espresso.html#onView(org.hamcrest.Matcher<android.view.View>)) method accepts a matcher argument and search in the view hieararchy
      The class name of the view
      The content description of the view
      The R.id of the view
      Text displayed in the view
        Example:  ```onView(withId(R.id.my_button));```
 2. Action API
 3. UI thread synchronization
 
- Setup activity
   - ActivityTestRule [example](https://github.com/nishashirawala/YelpRestaurantApp/blob/master/app/src/androidTest/java/com/yelprestaurantapp/SearchActivityInstrumentation.java) 
   ```public ActivityTestRule mActivityRule = new ActivityTestRule<>(SearchActivity.class);```
   - LaunchActivity With Intent [example] (https://github.com/nishashirawala/YelpRestaurantApp/blob/master/app/src/androidTest/java/com/yelprestaurantapp/MainActivityInstrumentation.java)
   ``` Intent intent = new Intent(mContext, MainActivity.class);
            intent.putExtra("searchLocation", "toronto");
            intent.putExtra("searchLimit", "20");
            launchActivityWithIntent("com.yelprestaurantapp", MainActivity.class, intent);```
