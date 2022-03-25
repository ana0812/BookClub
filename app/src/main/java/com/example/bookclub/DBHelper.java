package com.example.bookclub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.bookclub.Models.BookModel;
import com.example.bookclub.Models.SummaryModel;
import com.example.bookclub.Models.UserModel;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    //DataBase info
    private static final String DB_NAME = "BookClub.db";
    private static final int DB_VERSION = 5;

    //common column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    //users table
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PASSWORD = "password";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS
            + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +  COLUMN_NAME + " TEXT, "
            + COLUMN_USER_EMAIL + " TEXT UNIQUE, "
            + COLUMN_USER_PASSWORD + " TEXT);";

    //books table
    private static final String TABLE_BOOKS = "books";
    private static final  String COLUMN_BOOKS_AUTHOR = "author";
    private static final  String COLUMN_BOOKS_PHOTO = "urlPhoto";

    private static final String CREATE_TABLE_BOOKS = "CREATE TABLE " + TABLE_BOOKS
            + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +  COLUMN_NAME + " TEXT, "
            + COLUMN_BOOKS_AUTHOR + " TEXT, "
            + COLUMN_BOOKS_PHOTO + " TEXT);";

    //other common book columns
    private static final  String COLUMN_USERID = "userID";
    private static final  String COLUMN_BOOKID = "bookID";

    //summaries table
    private static final String TABLE_SUMMARIES = "summaries";
    private static final  String COLUMN_SUMMARIES_SUMMARY = "summary";
    private static final  String COLUMN_SUMMARIES_VISIBILITY = "visible";

    private static final String CREATE_TABLE_SUMMARIES = "CREATE TABLE " + TABLE_SUMMARIES
            + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +  COLUMN_USERID + " TEXT, "
            + COLUMN_BOOKID + " TEXT, "
            + COLUMN_SUMMARIES_SUMMARY + " TEXT, "
            + COLUMN_SUMMARIES_VISIBILITY + " INTEGER, "
            + "FOREIGN KEY (" + COLUMN_USERID + ") REFERENCES " + TABLE_USERS + " (" + COLUMN_ID + "), "
            + "FOREIGN KEY (" + COLUMN_BOOKID + ") REFERENCES " + TABLE_BOOKS + " (" + COLUMN_ID + ") "
            + ");";
    //+ "FOREIGN KEY (" + COLUMN_BOOKID + ") REFERENCES " + TABLE_BOOKS + " (" + COLUMN_ID + ") "

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
        sqLiteDatabase.execSQL(CREATE_TABLE_BOOKS);
        sqLiteDatabase.execSQL(CREATE_TABLE_SUMMARIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(sqLiteDatabase);
    }

    /**
     * Adds a user into the db
     * @param u
     * @return
     */
    public boolean addUser(UserModel u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, u.getName());
        cv.put(COLUMN_USER_EMAIL, u.getEmail());
        cv.put(COLUMN_USER_PASSWORD, u.getPassword());

        long insert = db.insert(TABLE_USERS, null, cv);
        return insert!=-1;

    }

    /**
     * Checks if a user exists in the db, based on email & password
     * @param u
     * @return true for success, false for failure
     */
    public boolean checkUser(UserModel u){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?",
                                         new String []{u.getEmail(), u.getPassword()});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            Log.i("utilizator gasit", cursor.getString(1) +" "+ cursor.getString(2) + " "+ cursor.getString(3));
            u.setName(cursor.getString(1));
            u.setId(cursor.getInt(0));
            return true;
        }

        return false;
    }

    /**
     * Adds a book into the db
     * @param b
     * @return true for success, false for failure
     */
    public boolean addBook(BookModel b){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, b.getName());
        cv.put(COLUMN_BOOKS_AUTHOR, b.getAuthor());
        cv.put(COLUMN_BOOKS_PHOTO, b.getUrlPhoto());

        long insert = db.insert(TABLE_BOOKS, null, cv);
        return insert!=-1;
    }

    /**
     * Searches for a book by name in the db
     * @param name
     * @return if the book with the parameter name is found:
     *              the book as instance of the BookModel class
     *         else
     *              null
     */
    public BookModel findBookByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books WHERE name = ?" ,
                                    new String []{name} );

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            BookModel book = new BookModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            Log.i("book found", book.toString());
            return book;
        }

        return null;
    }

    public boolean addSummary(SummaryModel s){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERID, s.getUserId());
        cv.put(COLUMN_BOOKID, s.getBookID());
        cv.put(COLUMN_SUMMARIES_SUMMARY, s.getSummary());
        cv.put(COLUMN_SUMMARIES_VISIBILITY, s.getVisible());

        long insert = db.insert(TABLE_SUMMARIES, null, cv);
        return insert!=-1;
    }

    public boolean findSummary(int userId, int bookId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM summaries WHERE userID = ? AND bookID = ?" ,
                new String []{String.valueOf(userId), String.valueOf(bookId)} );

        if(cursor.getCount()>0)
            return true;

        return false;
    }
}
