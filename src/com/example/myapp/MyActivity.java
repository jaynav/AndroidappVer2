package com.example.myapp;

import DBLayer.TDB;
import android.app.ListActivity;
import android.content.Intent; import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
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

        ///// db connect // Start up DB connection (closed in onDestroy).
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
       OtherMethodJunk.DahBinder(mCursorAdapter);

        // Alternative: also have row.xml layout with just one text field. No ViewBinder
        // needed for that simpler approach.
        setListAdapter(mCursorAdapter);
        registerForContextMenu(getListView());

        modeView =getListView();
         OtherMethodJunk.setListView(getListView(), this);
        //read preferences too much crap on OnCreate....made own metherd
         loadDefaultPef();
    }

    //on resume goes here
    @Override
    public void onResume()
    {
        super.onResume();
        if(omg)
        {
            SharedPreferences preference = getSharedPreferences("derp", 0);
            txtChange.setText(preference.getString("DefaultValue",""));
        }
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
        omg = true;

    }

    public void loadDefaultPef() {
        txtChange = (TextView) findViewById(R.id.textView);
        SharedPreferences preference = getSharedPreferences("derp", 0);
        SharedPreferences.Editor edt = preference.edit();
         String test = preference.getString("SetDefault","");
        if(!test.equals("True"))
        {
            edt.putString("DefaultValue", this.getString(R.string.PreferenceView));
            edt.putString("SetDefault", "True");
            edt.commit();
        }

        txtChange.setText(preference.getString("DefaultValue",""));
        omg = false;
    }
        ///////////////////////////////////////////////////////////////////////////
public static final String EXTRA_ROWID = "rowid";
    private SimpleCursorAdapter mCursorAdapter;
    private TDB mDb;    ActionMode activeMode;
    ListView modeView;    long dtaHelper;
    TextView txtChange;    boolean omg;
}