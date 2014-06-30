package com.example.myapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import DBLayer.DBAccess;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyActivity extends ListActivity {

    private DBAccess accDB;
    /** Called when the activity is first created.*/
 //on create goes here
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //search for add btn
        Button findBtn = (Button) findViewById(R.id.NewItemBtn);
      //lambda not supported in dalvik.. boo
      // findBtn.setOnClickListener(lambdaView -> editListDetail(0, true));
        findBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editListDetail(0,true);
            }
        });
        ////////////////////////////////////////////end on click listener
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

    private static final String derRow ="rowid";
}