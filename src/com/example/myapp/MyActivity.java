package com.example.myapp;

import DBLayer.TDB;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MyActivity extends ListActivity {


    /** Called when the activity is first created.*/
 //on create goes here
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ///// db connect
        // Start up DB connection (closed in onDestroy).
        mDb = new TDB(this);
        mDb.open();

        // Get the "all rows" cursor. startManagingCursor() is built in for the common case,
        // takes care of closing etc. the cursor.
        Cursor cursor = mDb.queryAll();
        startManagingCursor(cursor);

        // Adapter: maps cursor keys, to R.id.XXX fields in the row layout.
        //sets it back to listview
        String[] from = new String[] { TDB.KEY_TITLE, TDB.KEY_BODY, TDB.KEY_STATE };
        int[] to = new int[] { R.id.listViewDetail, R.id.listViewDetail2 ,R.id.imageView };
        mCursorAdapter = new SimpleCursorAdapter(this, R.layout.listviewlayout, cursor, from, to);



        //change this to an icon instead
       // Map "state" int to text in the row -- intercept the setup of each row view,

        mCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (columnIndex == TDB.INDEX_STATE) {
                    //TextView txView = (TextView) view;
                    ImageView imView = (ImageView) view;
                    if (cursor.getInt(TDB.INDEX_STATE) > 0) {
                        //txView.setText(" (done) ");
                        imView.setImageResource(R.drawable.likebtn);
                    }
                    else {
                        //txView.setText("Not");
                        imView.setImageResource(R.drawable.th);
                    }
                    return true;  // i.e. we handled it
                }
                return false;  // i.e. the system should handle it
            }
        });

        // Alternative: also have row.xml layout with just one text field. No ViewBinder
        // needed for that simpler approach.

        setListAdapter(mCursorAdapter);
        registerForContextMenu(getListView());
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
      mDb.close();
    }
//part of actionBar menu items

  /*  public  boolean onOptionsItemSelected(MenuItem itm)
    {
        switch (itm.getItemId())
    {

    }
    }
*/
//normal methods not related to how android operates

    public void onListItemClick(ListView lv, View deView, int pos, long rowI)
    {
        super.onListItemClick(lv,deView, pos, rowI);
        editListDetail(rowI,false);
    }

    private void editListDetail(long rowId, boolean b)
    {
        Intent derIntent = new Intent(this, ShowListDetail.class);
        if(! b)
        {
            derIntent.putExtra(EXTRA_ROWID, rowId);
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


    public void remove(long rowI)
    {
        mDb.deleteRow(rowI);
        mCursorAdapter.getCursor().requery();/////////change this
    }
    ///////////////////////////////////////////////////////////////////

public static final String EXTRA_ROWID = "rowid";
    private SimpleCursorAdapter mCursorAdapter;
    private TDB mDb;
}