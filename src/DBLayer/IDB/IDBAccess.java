package DBLayer.IDB;

//import java.sql.SQLException;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 5thinstall on 6/23/2014.
 */
public interface IDBAccess {

    /**opens sql database connection*/
    void open() throws SQLException;

    /**closes database connection*/
    void close();

    /**creates row in datbase*/
    long createRow(ContentValues derValue);

    /**update the data row based on id*/
    boolean updateRow(long rowId,ContentValues derValue);

    /**deletes row in dba*/
    boolean deleteRow(long rowId);

    /**query's the whole dba */
    Cursor queryEverything();

    /**asks for a specific query*/
    Cursor normalQuery(long rowId);

    /** creates android value hash to hold data/pass it to create and update*/
    ContentValues createContentValue(String title, String body, int state);



}
