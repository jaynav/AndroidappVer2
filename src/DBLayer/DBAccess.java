package DBLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.provider.ContactsContract;

/**
 * Created by 5thinstall on 6/23/2014. 4 column database
 */
public class DBAccess extends DbBase {

//////////////////////////constructor//////////////////////////////////////
    public DBAccess(Context dContext)
{

    this.derContext = dContext;

}
/////////////////////////////methods/////////////////////////////

    /**
     * opens sql database connection
     */
    @Override
    public void open() throws SQLException
    {
        derHelper = new HelpDBU.DBHelperUtility(derContext);
        derDatabase = derHelper.getWritableDatabase();
    }

    /**
     * closes database connection
     */
    @Override
    public void close()
    {
        derHelper.close();
        derHelper = null;
        derDatabase = null;
    }

    /**
     * creates row in datbase
     *
     * @param derValue value of content string to be inserted
     */
    @Override
    public long createRow(ContentValues derValue)

    {
        return derDatabase.insert(DATABASE_TABLE ,null, derValue);
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
        //return the update based on the table, string value, its location, and null for where clause since id already given
         //derDatabase.execSQL("UPDATE DATABASE_TABLE SET title='"&title&"',body='"& body &"' WHERE ID ='"&rowId&"' " );

        return derDatabase.update(DATABASE_TABLE, derValue, DBAccess.KEY_ROWID + "=" +rowId,null)> 0;
    }

    /**
     * deletes row in dba
     *
     * @param rowId row id
     */
    @Override
    public boolean deleteRow(long rowId)
    {

        return derDatabase.delete(DATABASE_TABLE,DBAccess.KEY_ROWID+ "="+rowId,null)> 0;

    }

    /**
     * query's the whole dba
     */
    @Override
    public Cursor queryEverything()

    {
        return derDatabase.query(DATABASE_TABLE, KEYS_ALL,null,null,null,null,DBAccess.KEY_TITLE + "ASC");

    }

    /**
     * asks for a specific query
     *
     * @param rowId row id
     */
    @Override
    public Cursor normalQuery(long rowId) throws SQLException
    {
        Cursor cursor = derDatabase.query(true,DATABASE_TABLE,KEYS_ALL, KEY_ROWID+""+rowId,null,null,null,null,null);
        cursor.moveToFirst();
        return cursor;
    }

    /**
     * creates android value hash to hold data/pass it to create and update that the ContentResolver can process
     * it is held in the contentvalues api class
     *
     * @param title title of object
     * @param body the body of object
     * @param state the state of such object
     */
    @Override
    public ContentValues createContentValue(String title, String body, int state)
    {
        ContentValues value = new ContentValues();
        value.put(KEY_TITLE,title);
        value.put(KEY_BODY, body);
        value.put(KEY_STATE, state);
        return value;
    }







}
