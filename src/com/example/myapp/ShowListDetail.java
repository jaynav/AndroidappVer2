package com.example.myapp;


import android.app.Activity;
import android.database.Cursor;
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
        chBxE = (CheckBox) findViewById(R.id.chBx1);

        //////
        mRowI = null;

        if(derBundle == null)
        { //used to edit existing row
            Bundle xtra = getIntent().getExtras();

            if(xtra != null && xtra.containsKey(MyActivity.EXTRA_ROWID))
            {
                //sets value based on key
                mRowI =xtra.getLong(MyActivity.EXTRA_ROWID);
            }
        }
        else
        {  //aimed for recovery purposes using on saveinstance to recover based on key
            mRowI =derBundle.getLong(Save_ROW);
        }


        /////////open new DB Instance
       mDb = new TDB(this);
        mDb.open();

        dbToUI();

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
       saveDataDB();
    }

    //on save goes here
    @Override
    public void onSaveInstanceState(Bundle outSaveState)

    {
        super.onSaveInstanceState(outSaveState);
        outSaveState.putLong(Save_ROW, mRowI);

    }

    //on destroy goes here
    public void  onDestroy()
    {
        super.onDestroy();
      mDb.close();
    }
///////////////////////////////////////////////////////
// the finish call forces onpause

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

    private void dbToUI() {
        if (mRowI != null) {
            Cursor cursor = mDb.query(mRowI);
            // Note: a cursor should be closed after use, or "managed".

            // Could use cursor.getColumnIndex(columnName) to look up 0, 1, ... index
            // for each column name. Here use INDEX_ consts from TodoDB.
            textVeneer1.setText(cursor.getString(TDB.INDEX_TITLE));
            textVeneer2.setText(cursor.getString(TDB.INDEX_BODY));
            chBxE.setChecked(cursor.getInt(TDB.INDEX_STATE) > 0);

            cursor.close();
        }
    }


    protected void saveDataDB()
    {


        if(!trashData)
        {
            String DerTitle = textVeneer1.getText().toString();
            String CenterSection = textVeneer2.getText().toString();
            int chBX = checkboxChecked(chBxE);

            if (mRowI ==null)
            {
                mRowI = mDb.createRow(mDb.createContentValues(DerTitle,CenterSection,chBX));
            }
            else
            {
                mDb.updateRow(mRowI, mDb.createContentValues(DerTitle,CenterSection,chBX));
            }
        }

    }

    private int checkboxChecked(CheckBox boxEditable)
    {
        if(boxEditable.isChecked())
        {
            return 1;
        }
        return 0;
    }



    //////////////////////////////fields/////////////////
    private  TDB mDb;

    private Long mRowI;

    private EditText textVeneer1, textVeneer2;
    private CheckBox chBxE;
    private boolean trashData;
    public static final String Save_ROW = "saverow";
}
