package android.net.http;

/**
 * This class is added due to following issue on robolectric
 * https://github.com/robolectric/robolectric/issues/1862
 * I am using shadowactivity in SearchActivityTest and it requires AndroidHttpClient.
 * If I add "useLibrary 'org.apache.http.legacy'" inside build.gradle, yelp api fails to get data from web.
 * So blank class created here.
 */
public class AndroidHttpClient {
}
