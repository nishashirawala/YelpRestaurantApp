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
- [Integration with [Travis CI] (https://travis-ci.org/)](#integration-with-travis-ci-httpstravis-ciorg)
- [Integration with Codacy](#integration-with-codacy)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# YelpRestaurantApp

[![build status](https://travis-ci.org/nishashirawala/YelpRestaurantApp.svg?branch=master)](https://travis-ci.org/nishashirawala/YelpRestaurantApp)
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
- To run all local unit tests in app module ```gradlew app:connectedAndroidTest```


# Integration with [Travis CI] (https://travis-ci.org/)
Travis CI is a hosted continuous integration service. You can sign up with a GitHub account. It’s free for open source projects.
You need to add ```.travis.yml``` configuration file for your project. If you want whenever a build succeeds, upload JaCoCo report to Codecov.

# Integration with [Codacy](https://www.codacy.com/)
...


