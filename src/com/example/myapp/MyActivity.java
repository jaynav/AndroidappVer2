package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
 //on create goes here
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    @Override
    //action bar
    public boolean onCreateOptionsMenu(Menu derMenu){
        getMenuInflater().inflate(R.menu.main, derMenu);
        return true;
    }

    //on resume goes here
    @Override
    public void onResume()
    {
        super.onResume();
    }

    //on pause goes here
    @Override
    public void onPause()
    {
        super.onPause();
    }

    //on save goes here
    @Override
    public void onSaveInstanceState(Bundle outSaveState)
    {
        super.onSaveInstanceState(outSaveState);
    }

    //on destroy goes here
    public void  onDestroy()
    {
      super.onDestroy();
    }
}

//normal methods not related to how android operates