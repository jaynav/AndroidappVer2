package DBLayer;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

/**
 * Created by ........ on 6/23/2014 creates DataBASE
 */
public class HelpDBU {

    /**
     * Database operations come from the sqliteopenhelper class
     */
    public static class DBHelperUtility extends SQLiteOpenHelper
    {

        public DBHelperUtility(Context context) {
            super(context, DBAccess.DATABASE_NAME, null, DBAccess.DATABASE_VERSION);
        }


        private static final String DATABASE_CREATE= "create table " + DBAccess.DATABASE_TABLE + " (" +
                DBAccess.KEY_ROWID + " integer primary key autoincrement, " +
                DBAccess.KEY_TITLE + " text not null, " +
                DBAccess.KEY_BODY + " text not null," +
                DBAccess.KEY_STATE + " integer " +
                ");";



        /** Creates the initial (empty) database. */
        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(DATABASE_CREATE);
        }


        /** Called at version upgrade time, in case we want to change/migrate
         the database structure. do nothing. */
        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            // do nothing for this case
        }
    }

}
