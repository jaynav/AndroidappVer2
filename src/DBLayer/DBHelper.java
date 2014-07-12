package DBLayer;

import DBLayer.HelperInterAbs.DBHelpInterFaceAbs;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by 5thinstall on 7/2/2014.
 */
public class DBHelper extends DBBase{

    protected static class TodoDBHelper extends DBHelpInterFaceAbs {
        // SQL text to create table (basically just string or integer)
        private static final String DATABASE_CREATE =
                "create table " + DATABASE_TABLE + " (" +
                        TDB.KEY_ROWID + " integer primary key autoincrement, " +
                        TDB.KEY_TITLE + " text not null, " +
                        TDB.KEY_BODY + " text not null," +
                        TDB.KEY_STATE + " integer " +
                        ");";

        // SQLITE does not have a complex type system, so although "done" is a boolean
        // to the app, here we store it as an integer with (0 = false)


        public TodoDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /** Creates the initial (empty) database. */
        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(DATABASE_CREATE);
        }


        /** Called at version upgrade time, in case we want to change/migrate
         the database structure. Here we just do nothing. */
        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            // we do nothing for this case
        }
    }

}
