package adudek.metronom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by a on 2017-06-01.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //private - dostepna tylko z poziomu tej klasy
    //static - klasa nalezy nie do konkretnej instancji,
    //ale do calej klasy
    //final - nie mozna jej zmienic, wartosc ustalona
    //int - integer
    //w przypadku gdy zmieniam/migruje baze musze zmienic wersje
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "musicApp";

    //tabela piosenek - piosenka zawiera id, tytul
    private static final String TABLE_SONGS = "songs";
    private static final String id = "id";
    private static final String title = "title";
    private static final String extraInformations = "exInfo";
    private static final String speedRate = "speedRate" ; // for example 180
    private static final String  measure = "measure"; // for example 2
    private static final String duration = "duration"; // in seconds

    //tabela koncertow - koncert zawiera id, nazwa unikalna, piosenka_id
    private static final String TABLE_CONCERTS = "concerts";
    private static final String con_id = "id";
    private static final String name = "name";
    private static final String song_id = "song_id";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tworzy tabele piosenek
        String CREATE_SONGS_TABLE = "CREATE TABLE " + TABLE_SONGS + "("
                + id + " INTEGER PRIMARY KEY," + title + " TEXT,"
                + extraInformations + " TEXT," + speedRate + " TEXT," +
                measure + " TEXT," + duration + " TEXT" +")";
        db.execSQL(CREATE_SONGS_TABLE);

        //tworzy tabele koncertow, ktora tak naprawde musi byc iterowana po nazwie
        String CREATE_CONCERTS_TABLE = "CREATE TABLE " + TABLE_CONCERTS + "("
                + con_id + " INTEGER PRIMARY KEY," + name + " TEXT,"
                + song_id + " TEXT" + ")";

        db.execSQL(CREATE_CONCERTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONCERTS);

        // Create tables again, tworzy obydwie od razu
        onCreate(db);
    }

    public boolean dbExists () {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(DATABASE_NAME, null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e)
        {

        }
        return checkDB != null;
    }
    // Adding new contact
    public void addSong(Song contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(id, contact.getId_Song()); // Contact Name
        values.put(title, contact.getTitle()); // Contact Name
        values.put(extraInformations, contact.getExtraInformations()); // Contact Name

        //wszystkie inty przechowuje jako text, wiec rzutuje
        values.put(speedRate, String.valueOf(contact.getSpeedRate())); // Contact Name
        values.put(measure, String.valueOf(contact.getMeasure())); // Contact Name
        values.put(duration, String.valueOf(contact.getDuration())); // Contact Name

        // Inserting Row
        db.insert(TABLE_SONGS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Song getSong(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SONGS, new String[] { this.id,
                        title, extraInformations, speedRate, measure, duration  }, this.id + "=?",
                //to podstawia parametr id pod zapytanie
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Song contact = new Song((cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5));
        // zwraca piosenke z uzyciem konstruktora dedykowanego dla bazy
        return contact;
    }

    // Getting All Contacts
    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> contactList = new ArrayList<Song>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SONGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Song contact = new Song();
                contact.setId_Song(Integer.parseInt(cursor.getString(0)));
                contact.setTitle(cursor.getString(1));
                contact.setExtraInformations(cursor.getString(2));
                contact.setSpeedRate(Integer.parseInt(cursor.getString(3)));
                contact.setMeasure(Integer.parseInt(cursor.getString(4)));
                contact.setDuration(Integer.parseInt(cursor.getString(5)));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Getting contacts Count
    public int getSongCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SONGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    // Updating single contact
    public int updateSong(Song contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(title, contact.getTitle());
        values.put(extraInformations, contact.getExtraInformations()); // Contact Name

        //wszystkie inty przechowuje jako text, wiec rzutuje
        values.put(speedRate, String.valueOf(contact.getSpeedRate())); // Contact Name
        values.put(measure, String.valueOf(contact.getMeasure())); // Contact Name
        values.put(duration, String.valueOf(contact.getDuration())); // Contact Name

        // updating row
        return db.update(TABLE_SONGS, values, id + " = ?",
                new String[] { String.valueOf(contact.getId_Song()) });
    }

    // Deleting single contact
    public void deleteSong(Song contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SONGS, id + " = ?",
                new String[] { String.valueOf(contact.getId_Song()) });
        db.close();
    }

    // Adding new contact
    public void addConcert(Concert contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(con_id, contact.getId_Concert()); // Contact Name
        values.put(name, contact.getName()); // Contact Name
        values.put(song_id, contact.getListOfSongIds()); // Contact Name
//
//

        // Inserting Row
        db.insert(TABLE_CONCERTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Concert getConcert(int id) { return null;}

    // Getting All Contacts
    public ArrayList<Concert> getAllConcert() {return null;}

    // Getting contacts Count
    public int getConcertCount() {return 0;}
    // Updating single contact
    public int updateConcert(Concert contact) {return 0;}

    // Deleting single contact
    public void deleteConcert(Concert contact) {}
}
