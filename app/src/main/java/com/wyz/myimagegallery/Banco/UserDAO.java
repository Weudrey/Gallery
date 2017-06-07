package com.wyz.myimagegallery.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wyz.myimagegallery.classes.User;


/**
 * Created by W€µÐr€Y™ on 06/06/2017.
 */

public class UserDAO extends SQLiteOpenHelper{

        private static final int DATABASE_VERSION = 6;

        private static final String DATABASE_NAME = "banco.db";

        private static final String TABLE_USER = "USER";

        private static final String COLUMN_USER_ID = "_user_id";
        private static final String COLUMN_USER_NAME = "user_name";
        private static final String COLUMN_USER_EMAIL = "user_email";
        private static final String COLUMN_USER_PASSWORD = "user_password";

        private String CREATE_USER_TABLE = "CREATE TABLE "
                + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_EMAIL + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT" + ")";

        private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

        public UserDAO(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);  }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(CREATE_USER_TABLE);
        }

        @Override
        public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL(DROP_USER_TABLE);
            onCreate(db);
        }

        public void addUser(User user){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, user.getName());
            values.put(COLUMN_USER_EMAIL, user.getEmail());
            values.put(COLUMN_USER_PASSWORD, user.getPassword());

            db.insert(TABLE_USER, null, values);
            db.close();
        }

        public boolean checkUser(String email){
            String[] columns = {
                    COLUMN_USER_ID
            };
            SQLiteDatabase db = this.getWritableDatabase();
            String selection = COLUMN_USER_EMAIL + " = ?";
            String[] selectionArgs = { email };

            Cursor cursor = db.query(TABLE_USER,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null);
            int cursorCount = cursor.getCount();
            cursor.close();
            db.close();

            if (cursorCount > 0){
                return true;
            }
            return false;
        }

        public boolean checkUser(String email, String password){
            String[] columns = {
                    COLUMN_USER_ID
            };
            SQLiteDatabase db = this.getWritableDatabase();
            String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " =?";
            String[] selectionArgs = { email, password };

            Cursor cursor = db.query(TABLE_USER,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null);
            int cursorCount = cursor.getCount();
            cursor.close();
            db.close();

            if (cursorCount > 0){
                return true;
            }
            return false;
        }

}
