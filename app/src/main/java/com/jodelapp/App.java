package com.jodelapp;


import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerDependencies.Companion.init(this);
    }
}
