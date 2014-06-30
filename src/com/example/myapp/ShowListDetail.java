package com.example.myapp;

import DBLayer.DBAccess;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by ... on 6/23/2014. add activity to manifest
 */
public class ShowListDetail extends Activity {

    @Override
    public void onCreate(Bundle derBundle) {
        super.onCreate(derBundle);
        setContentView(R.layout.list_detail);

        //find and set data for textboxes
        textVeneer1 = (EditText) findViewById(R.id.detailTextView1);
        textVeneer2 = (EditText) findViewById(R.id.detailTextView2);
        boxEditable = (CheckBox) findViewById(R.id.chBx1);

        //////
        rowIdToEdit = null;

        if(derBundle == null)
        { //used to edit existing row
            Bundle xtra = getIntent().getExtras();

            if(xtra != null && xtra.containsKey(MyActivity.derRow))
            {
                //sets value based on key
                rowIdToEdit =xtra.getLong(MyActivity.derRow);
            }
        }
        else
        {  //aimed for recovery purposes using on saveinstance to recover based on key
            rowIdToEdit =derBundle.getLong(Save_ROW);
        }




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
///////////////////////////////////////////////////////


    public void cancelData(View derView)
    {
        trashData = true;
        finish();
    }
    public void saveData(View derView)
    {
        trashData = false;
        finish();
    }
    //////////////////////////////fields/////////////////
    private DBAccess derDB;
    private Long rowIdToEdit;
    private EditText textVeneer1, textVeneer2;
    private CheckBox boxEditable;
    private boolean trashData;
    public static final String Save_ROW = "saverow";
}
