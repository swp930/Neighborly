package com.neighborly.swapnilpatil.neighborly;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by vinam on 8/27/2016.
 */
public class NeighborlyApplication extends Application {

    private static boolean firstTimeLogin = false;
    public static String baseUrlToUpload = "10.3.17.144";
    public static boolean isFirstTimeLogin() {
        return firstTimeLogin;
    }

    public static void setFirstTimeLogin(boolean firstTimeLogin) {
        NeighborlyApplication.firstTimeLogin = firstTimeLogin;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }



}
