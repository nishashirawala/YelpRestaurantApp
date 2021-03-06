<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [YelpRestaurantApp](#yelprestaurantapp)
  - [Running Tests](#running-tests)
    - [UnitTesting](#unittesting)
      - [In Android Studio](#in-android-studio)
      - [From command-line via Gradle](#from-command-line-via-gradle)
    - [Instrumentation Tests](#instrumentation-tests)
      - [In Android Studio](#in-android-studio-1)
      - [From command-line via Gradle](#from-command-line-via-gradle-1)
- [Unit Testing with Robolectric](#unit-testing-with-robolectric)
- [UI Testing with Espresso](#ui-testing-with-espresso)
- [Integration with Circle CI](#integration-with-circle-ci)
- [Integration with Travis CI](#integration-with-travis-ci)
- [Integration with Codacy](#integration-with-codacy)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# YelpRestaurantApp

[![build status](https://travis-ci.org/nishashirawala/YelpRestaurantApp.svg?branch=master)](https://travis-ci.org/nishashirawala/YelpRestaurantApp)
[![CircleCI](https://circleci.com/gh/nishashirawala/YelpRestaurantApp.svg?style=svg)](https://circleci.com/gh/nishashirawala/YelpRestaurantApp)
[![codecov](https://codecov.io/gh/nishashirawala/YelpRestaurantApp/branch/master/graph/badge.svg)](https://codecov.io/gh/nishashirawala/YelpRestaurantApp)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e948f0d7b8cf4b0f8ac6a113df8f7a0e)](https://www.codacy.com/app/nishashirawala/YelpRestaurantApp)

Android app to show restaurant list from yelp.

## Running Tests 
### UnitTesting
#### In Android Studio
- In the Build Variants window, make sure the Unit Tests option is selected.
- Open package app-> src -> test -> java
- Right click on it and select ```Run All tests```

#### From command-line via Gradle
- To run all local unit tests in app module ```gradlew app:test```

### Instrumentation Tests
#### In Android Studio
- In the Build Variants window, make sure the Android Instrumentation Tests option is selected.
- Open package app-> src -> androidTest -> java 
- Right click on it and select ```Run All tests```

#### From command-line via Gradle
You need to have andrid emulator already running for this command
- To run all local instrumentation tests in app module ```gradlew app:connectedAndroidTest``` or ```gradlew app:cAT```

# Unit Testing with [Robolectric](http://robolectric.org/)
Robolectric is a testing framework that de-fangs the Android SDK so you can test-drive the development of your Android app.
Git page - https://github.com/robolectric/robolectric

# UI Testing with [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/)
Espresso is a testing framework for Android to make it easy to write reliable user interface tests for a single application. 
Espresso automatically synchronizes your test actions with the user interface of your application. The framework also ensures that your activity is started before the tests run. It also let the test wait until all observed background activities have finished.

Setup Espresso by adding following to build.gradle
```
dependencies {
  ...
  androidTestCompile 'com.android.support.test.espresso:espresso-core:2.2.1'
  ...
}
```

Espresso has basically three components:

1. ViewMatchers - allows to find view in the current view hierarchy
2. ViewActions - allows to perform actions on the views
3. ViewAssertions - allows to assert state of a view

The case construct for Espresso tests is the following:

     onView(ViewMatcher)    
      .perform(ViewAction)   
        .check(ViewAssertion); 
  -  Finds the view
  -  Performs an action on the view
  -  Validates a assertion

  
# Integration with [Circle CI](https://circleci.com/)
Add a project to Circle CI and add ```circle.yml``` configuration file.
  
# Integration with [Travis CI](https://travis-ci.org/)
Travis CI is a hosted continuous integration service. You can sign up with a GitHub account. It’s free for open source projects.
You need to add ```.travis.yml``` configuration file for your project. If you want whenever a build succeeds, upload JaCoCo report to Codecov.

If instrumentation tests fails with ShellCommandUnresponsiveException then increase timeout in .travis.xml 
 ```- ADB_INSTALL_TIMEOUT=8 # minutes (2 minutes by default)```

# Integration with [Codacy](https://www.codacy.com/)
Easy integration with git project. Just need to include ```.codecov.yml``` file and make appropriate changes in ```.travis.yml``` or ```circle.yml``` file to upload reports to Codecov
