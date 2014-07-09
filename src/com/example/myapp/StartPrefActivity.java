package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.PreferenceFragment;


/**
 * Created by 5thinstall on 7/7/2014.
 */
public class StartPrefActivity extends Activity {




    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefFragmentDetail()).commit();

        PreferenceManager.setDefaultValues(this,R.xml.my_preferences, false);
    }




}
