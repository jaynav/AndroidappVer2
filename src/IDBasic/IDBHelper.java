package IDBasic;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 5thinstall on 7/2/2014.
 */
public interface IDBHelper {
    void onCreate(SQLiteDatabase database);
    void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion);
}
