package DBLayer;

import DBLayer.IDB.IDBAccess;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 5thinstall on 6/23/2014.
 */
public abstract class DbBase implements IDBAccess{

    //////////////////////fields///////////////////////////////////
    protected Context derContext;
    protected SQLiteDatabase derDatabase;
    protected HelpDBU.DBHelperUtility derHelper;


    //////////db definitions////////////////////////////
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dummyData";
    public static final String DATABASE_TABLE = "addJunk";
//4 column table

    public static final String KEY_ROWID = "_id";  // Android requirement
    public static final int INDEX_ROWID = 0;
    public static final String KEY_TITLE = "title";
    public static final int INDEX_TITLE = 1;
    public static final String KEY_BODY = "body";
    public static final int INDEX_BODY = 2;
    public static final String KEY_STATE = "state";
    public static final int INDEX_STATE = 3;
    public static final String[] KEYS_ALL =
            {
                    DBAccess.KEY_ROWID, DBAccess.KEY_TITLE, DBAccess.KEY_BODY, DBAccess.KEY_STATE
            };
}
