package com.example.myapp;

import DBLayer.TDB;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by DerpPC on 7/11/2014.
 */
public class OtherMethodJunk {
    public static void setListView(ListView listView, MyActivity myActivity)
    {
        //aimed  for context/ actionmode menu
        listView.setLongClickable(true);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemLongClickListener(myActivity);
    }

    public static void DahBinder(SimpleCursorAdapter mCursorAdapter)
    {
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
    }
}
