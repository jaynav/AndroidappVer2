package DBLayer;

import DBLayer.HelperInterAbs.TDBInterFaceAbstraction;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * changing metherd
 *
 */
public class TDB  extends TDBInterFaceAbstraction {

    /** Constructor DB for this activity context. */
    public TDB(Context context) {
        mContext = context;
    }

	/** Opens up a connection to the database. Do this before any operations. */
	public void open() throws SQLException {
		mHelper = new DBHelper.TodoDBHelper(mContext);
		mDatabase = mHelper.getWritableDatabase();
	}

	/** Closes the database connection. Operations are not valid after this. */
	public void close() {
		mHelper.close();
		mHelper = null;
		mDatabase = null;
	}


	/**
	  Creates and inserts a new row using the given values.
	  Returns the rowid of the new row, or -1 on error.
	  todo: values should not include a rowid I assume.
	 */
	public long createRow(ContentValues values) {
		return mDatabase.insert(DATABASE_TABLE, null, values);
	}

	/**
	 Updates the given rowid with the given values.
	 Returns true if there was a change (i.e. the rowid was valid).
	 */
	public boolean updateRow(long rowId, ContentValues values) {
		return mDatabase.update(DATABASE_TABLE, values,
				TDB.KEY_ROWID + "=" + rowId, null) > 0;
	}

	/**
	 Deletes the given rowid.
	 Returns true if any rows were deleted (i.e. the id was valid).
	*/
	public boolean deleteRow(long rowId) {
		return mDatabase.delete(DATABASE_TABLE,
				TDB.KEY_ROWID + "=" + rowId, null) > 0;
	}

	
	/** Returns a cursor for all the rows. Caller should close or manage the cursor. */
	public Cursor queryAll() {
		return mDatabase.query(DATABASE_TABLE,
			KEYS_ALL,  // i.e. return all 4 columns 
			null, null, null, null,
			TDB.KEY_TITLE + " ASC"  // order-by, "DESC" for descending
		);

		// Could pass for third arg to filter in effect:
		// TodoDatabaseHelper.KEY_STATE + "=0"

		// query() is general purpose, here we show the most common usage.
	}

	/** Returns a cursor for the given row id. Caller should close or manage the cursor. */
	public Cursor query(long rowId) throws SQLException {
		Cursor cursor = mDatabase.query(true, DATABASE_TABLE,
			KEYS_ALL,
			KEY_ROWID + "=" + rowId,  // select the one row we care about
			null, null, null, null, null);
		
		// cursor starts before first -- move it to the row itself.
		cursor.moveToFirst();
		return cursor;
	}

	/** Creates a ContentValues hash for our data. Pass in to create/update. */
	public ContentValues createContentValues(String title, String body, int state) {
		ContentValues values = new ContentValues();
		values.put(TDB.KEY_TITLE, title);
		values.put(TDB.KEY_BODY, body);
		values.put(TDB.KEY_STATE, state);
		return values;
	}
	
	// Helper for database open, create, upgrade.
	// Here written as a private inner class to TDB.

///////////////////////////////////////////////////////////
private Context mContext;
    private SQLiteDatabase mDatabase;
    private DBHelper.TodoDBHelper mHelper;

}
