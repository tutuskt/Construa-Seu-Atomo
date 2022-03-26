package br.com.danieljunior.bohrapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by danieljr on 07/02/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "bohrapp";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS elementos_quimicos";

    private SQLiteDatabase dataBase;

    private final Context context;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tableCreate = getElementosTableCreate();
        tableCreate = tableCreate.replace("`", "").replace("\t", " ").replace("\"", "");
        String[] statements = tableCreate.split(";");
        for (String statement : statements) {
            sqLiteDatabase.execSQL(statement + ";");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
    }

    private String getElementosTableCreate() {
        String response = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("elementos.sql")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                response += mLine;
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return response;
    }
}