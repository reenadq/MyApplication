package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ebook.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BOOKS = "books";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_AUTHOR_NAME = "author_name";
    private static final String COLUMN_BOOK_TITLE = "book_title";
    private static final String COLUMN_BOOK_CATEGORY = "book_category";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_BOOKS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_AUTHOR_NAME + " TEXT, " +
                COLUMN_BOOK_TITLE + " TEXT, " +
                COLUMN_BOOK_CATEGORY + " TEXT)";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_BOOKS;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public void insertBook(String authorName, String bookTitle, String bookCategory) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_AUTHOR_NAME, authorName);
        values.put(COLUMN_BOOK_TITLE, bookTitle);
        values.put(COLUMN_BOOK_CATEGORY, bookCategory);
        db.insert(TABLE_BOOKS, null, values);
        db.close();
    }

    public List<String> getAllBookTitles() {
        List<String> bookTitles = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BOOKS, new String[]{COLUMN_BOOK_TITLE}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String bookTitle = cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_TITLE));
                bookTitles.add(bookTitle);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bookTitles;
    }
}