package com.example.myapp;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by 5thinstall on 7/8/2014.
*/
public class PrefFragmentDetail extends PreferenceFragment {

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.my_preferences);

        // SharedPreferences inputValue = getSharedPreferences("dummyPreferenceFile", MODE_PRIVATE); //or 0
        // inputValue.edit().putString("PreferenceView","").commit();



    }


}