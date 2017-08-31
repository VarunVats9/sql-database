package com.example.vvats.sqldatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by vvats on 25/03/17.
 */

public class CustomSqlLiteDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "College";
    private static final String TABLE_NAME = "student";
    private static final String COL_PRIMARY_KEY = "_id";
    private static final String COL_STUDENT_NAME = "student_name";
    private static final String COL_COLLEGE_NAME = "college_name";
    private static final String BLANK_SPACES = "        ";
    private static final int VERSION = 1;

    Context context;
    SQLiteDatabase database;

    public CustomSqlLiteDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ("+COL_PRIMARY_KEY+" integer PRIMARY KEY autoincrement, "+COL_STUDENT_NAME+" TEXT, "+COL_COLLEGE_NAME+" TEXT);");
                //new Object[]{TABLE_NAME, COL_PRIMARY_KEY, COL_STUDENT_NAME, COL_COLLEGE_NAME});
        Log.i("Database", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists ?", new Object[]{TABLE_NAME});
        onCreate(sqLiteDatabase);
    }

    public void saveData(String name, String college) {
        database = getWritableDatabase();
        database.execSQL("insert into "+TABLE_NAME+"("+COL_STUDENT_NAME+", "+COL_COLLEGE_NAME+") values('"+name+"', '"+college+"');");
                //new Object[]{TABLE_NAME, COL_STUDENT_NAME, COL_COLLEGE_NAME, name, college});
        Log.i("Database", "Data saved");
    }

    public void loadData() {
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+";", null);
        StringBuilder stringBuilder = new StringBuilder();
        while (cursor.moveToNext()) {
            stringBuilder.append(cursor.getString(1) + BLANK_SPACES + cursor.getString(2) + "\n");
        }
        Toast.makeText(context, stringBuilder, Toast.LENGTH_LONG).show();
    }
}
