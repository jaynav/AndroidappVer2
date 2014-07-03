package DBLayer;

import IDBasic.IDBasic;


/**
 * Created by 5thinstall on 7/2/2014.
 * core abstraction of DB Layer
 */
public abstract class DBBase  {

    /////////////////////////////////////////////////////////////////////////////////////////////
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "derp";
    public static final String DATABASE_TABLE = "herp";

    // Field names -- use the KEY_XXX constants here and in
    // client code, so it's all consistent and checked at compile-time.

    public static final String KEY_ROWID = "_id";  // Android requires exactly this key name
   // public static final int INDEX_ROWID = 0;
    public static final String KEY_TITLE = "title";
    public static final int INDEX_TITLE = 1;
    public static final String KEY_BODY = "body";
    public static final int INDEX_BODY = 2;
    public static final String KEY_STATE = "state";
    public static final int INDEX_STATE = 3;

    public static final String[] KEYS_ALL =
            { TDB.KEY_ROWID, TDB.KEY_TITLE, TDB.KEY_BODY, TDB.KEY_STATE };






}
