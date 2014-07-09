package com.example.myapp;

import DBLayer.TDB;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.*;
import android.widget.*;

public class MyActivity extends ListActivity implements ActionMode.Callback, AdapterView.OnItemLongClickListener{


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

                    ImageView imView = (ImageView) view;
                    if (cursor.getInt(TDB.INDEX_STATE) > 0) {

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


        //aimed  for context/ actionmode menu
        modeView =getListView();
        getListView().setLongClickable(true);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setOnItemLongClickListener(this);


        //read preferences too much crap on OnCreate....made own metherd

         loadPref();


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
///////////////////////////////////////////////////part of actionBar menu items


    @Override
    //action bar
    public boolean onCreateOptionsMenu(Menu derMenu){
        getMenuInflater().inflate(R.menu.main, derMenu);
        return true;
    }
    public  boolean onOptionsItemSelected(MenuItem itm)
    {

        switch (itm.getItemId())
        {
            case R.id.actionBar_settings:
                startPreferenceDetail();
                return  true;
            default:
                return super.onOptionsItemSelected(itm);
        }


   }



   //////////////////////////////////////////////////////////context menu/actionMode
    public boolean onItemLongClick(AdapterView<?> view,View row, int position, long id)
    {
        modeView.clearChoices();
        modeView.setItemChecked(position,true);
        dtaHelper = id;
        if(activeMode == null)
        {
            activeMode= startActionMode(this);
        }
        return  true;

    }
   @Override
   public boolean onCreateActionMode(ActionMode mode, Menu menu)
   {
       mode.getMenuInflater().inflate(R.menu.contextual_acton_mode, menu);
       mode.setTitle("edit");
       return true;
   }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu)
    {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {


         switch (item.getItemId())
        {
            case R.id.contextualAction_remove:
                    removeStuff(dtaHelper);
                return true;
        default:
        return false;
    }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

        activeMode = null;
    }




////////////////////////////////////////////normal methods not related to how android operates

    public void onListItemClick(ListView lv, View deView, int pos, long rowI)
    {
        super.onListItemClick(lv,deView, pos, rowI);
        editListDetail(rowI,false);
    }



    public void  addStuff(View derVeiw)
    {
        try {
            editListDetail(0, true);
        }
        catch (Exception ex)
        {
           Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
            Log.e(ex.getMessage(),"error in addstuff");
        }
        }


    public void removeStuff(long rowI)
    {
        mDb.deleteRow(rowI);
        mCursorAdapter.getCursor().requery();/////////change this
    }

    /**
     * reading preference
     * made to get rid of crap on on create
     */
    public  void loadPref()
    {
       PreferenceManager.setDefaultValues(this, "omgt",MODE_PRIVATE, R.xml.my_preferences, false);
        txtToChange = (TextView) findViewById(R.id.textView);
       SharedPreferences shareMe = PreferenceManager.getDefaultSharedPreferences(this);
       //String dahValue = shareMe.getString("PValue", getString(R.string.PreferenceView));
       txtToChange.setText(shareMe.getString("PValue", getString(R.string.PreferenceView)));

    }
    ///////////////////////////////intent startr////////////////////////////////////

    private void editListDetail(long rowId, boolean b)
    {
        Intent derIntent = new Intent(this, ShowListDetail.class);
        if(! b)
        {
            derIntent.putExtra(EXTRA_ROWID, rowId);
        }
        startActivity(derIntent);

    }

    protected void startPreferenceDetail()
    {
        Intent derIntent = new Intent(this, StartPrefActivity.class);
        startActivity(derIntent);
    }

    ///////////////////////////////////////////////////////////////////////////
public static final String EXTRA_ROWID = "rowid";
    private SimpleCursorAdapter mCursorAdapter;
    private TDB mDb;
    ActionMode activeMode;
    ListView modeView;
    long dtaHelper;
    TextView txtToChange;
    StartPrefActivity demo;



}