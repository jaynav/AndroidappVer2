package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

/**
 * Created by ... on 6/23/2014. add activity to manifest
 */
public class ShowListDetail extends Activity {

    @Override
    public void onCreate(Bundle derBundle) {
        super.onCreate(derBundle);
        setContentView(R.layout.list_detail);



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
