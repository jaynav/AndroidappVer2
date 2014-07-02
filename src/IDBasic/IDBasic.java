package IDBasic;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

/**
 * Created by 5thinstall on 7/2/2014.
 */
public interface IDBasic {

    public void open() throws SQLException ;
    public void close();
    public long createRow(ContentValues values);
    public boolean updateRow(long rowId, ContentValues values);
    public boolean deleteRow(long rowId);
    public Cursor queryAll();
    public Cursor query(long rowId) throws SQLException;
    public ContentValues createContentValues(String title, String body, int state);
}
