package com.example.myapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import DBLayer.DBAccess;
import android.view.View;
import android.widget.Toast;

public class MyActivity extends ListActivity {


    /** Called when the activity is first created.*/
 //on create goes here
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startDB
      //  accDB = new DBAccess(this);
       // accDB.open();

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
        accDB.close();
    }


//normal methods not related to how android operates


    private void editListDetail(long rowId, boolean b)
    {
        Intent derIntent = new Intent(this, ShowListDetail.class);
        if(! b)
        {
            derIntent.putExtra(derRow, rowId);
        }
        startActivity(derIntent);

    }

    public void  addStuff(View derVeiw)
    {
        try {
            editListDetail(0, true);
        }
        catch (Exception ex)
        {
           Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG);
            Log.e(ex.getMessage(),"error in addstuff");
        }
        }
    ///////////////////////////////////////////////////////////////////
    private DBAccess accDB;
    protected static final String derRow ="rowid";
}