package com.example.isambataro.lesson2;

import android.app.Application;
import android.content.Context;

/**
 * Created by isambataro on 18/06/18.
 */

public class ApplicationContext extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return sContext;
    }
}
