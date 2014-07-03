package DBLayer.HelperInterAbs;

import IDBasic.IDBHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * implements SQLiteopenhelper with the implementation of the interface idbhelper
 */
public abstract class DBHelpInterFaceAbs extends SQLiteOpenHelper implements IDBHelper {
    public DBHelpInterFaceAbs(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
}
