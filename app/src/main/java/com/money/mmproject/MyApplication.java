package com.money.mmproject;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //This will only be called once in your app's entire lifecycle.
        Parse.initialize(this,
                "sb86WYyd0l1igfZY77sTtEWb0TVn3g067JORyVT6",
                "uQTRuM7tHPVzgctBOYQO6LmFpSBCNbAKYaFS8OmA");
        ParseUser.enableAutomaticUser();
        ParseACL defauAcl = new ParseACL();

        defauAcl.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defauAcl, true);
    }
}