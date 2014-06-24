package DBLayer;

import DBLayer.IDB.IDBAccess;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 5thinstall on 6/23/2014.
 */
public class DBAccess implements IDBAccess {

//////////////////////////constructor//////////////////////////////////////
    public DBAccess(Context derContext)
{
    this.derContext = derContext;

}
/////////////////////////////methods/////////////////////////////

    /**
     * opens sql database connection
     */
    @Override
    public void open() throws SQLException
    {

    }

    /**
     * closes database connection
     */
    @Override
    public void close()
    {

    }

    /**
     * creates row in datbase
     *
     * @param derValue value of content
     */
    @Override
    public long createRow(ContentValues derValue)
    {
        return 0;
    }

    /**
     * update the data row based on id
     *
     * @param rowId row id
     * @param derValue the value
     */
    @Override
    public boolean updateRow(long rowId, ContentValues derValue)
    {
        return false;
    }

    /**
     * deletes row in dba
     *
     * @param rowId row id
     */
    @Override
    public boolean deleteRow(long rowId)
    {
        return false;
    }

    /**
     * query's the whole dba
     */
    @Override
    public Cursor queryEverything()
    {
        return null;
    }

    /**
     * asks for a specific query
     *
     * @param rowId row id
     */
    @Override
    public Cursor normalQuery(long rowId)
    {
        return null;
    }

    /**
     * creates android value hash to hold data/pass it to create and update
     *
     * @param title title of object
     * @param body the body of object
     * @param state the state of such object
     */
    @Override
    public ContentValues createContentValue(String title, String body, int state)
    {
        return null;
    }






    ////////////////////variables/////////////////////////////////////
    private Context derContext;
    private SQLiteDatabase derDatabase;
    private HelpDBU.DBHelperUtility derHelper;


    //////////db definitions////////////////////////////
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dummyData";
    public static final String DATABASE_TABLE = "addJunk";


    public static final String KEY_ROWID = "_id";  // Android requires exactly this key name
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
