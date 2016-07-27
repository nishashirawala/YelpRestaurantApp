# Instrument Tests Using Espresso
- Write basic test as Junit4 test class and then use Espresso API to perform events on UI element.
- Espresso API works with android 2.2 (api level 8) or higher
- Key features
 1. View Matching
     - Espresso.onView() method accepts a matcher argument and search in the view hieararchy
      The class name of the view
      The content description of the view
      The R.id of the view
      Text displayed in the view
        Example:  ```onView(withId(R.id.my_button));```
 2. Action API
 3. UI thread synchronization
