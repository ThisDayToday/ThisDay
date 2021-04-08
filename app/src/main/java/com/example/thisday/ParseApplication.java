package com.example.thisday;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("n73Z5alc9BnBCpxpVi4zcpUJNHDNjGlpdIS85aMP")
                .clientKey("FHSDnsMISrf52JEYE00uzjrK5E3MCT20w0v5KwR6")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

}
