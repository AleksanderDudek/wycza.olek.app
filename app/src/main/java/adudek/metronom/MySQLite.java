package adudek.metronom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mateusz on 08.05.2017.
 */

public class MySQLite extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 2;

    public MySQLite(Context context)
    {
        super(context, "metronomDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(CreateSongTableString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS songs");
        onCreate(db);
    }

    public String CreateSongTableString()
    {
        return "create table songs(id_Song INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT NOT NULL," +
                "speedRate INTEGER NOT NULL," +
                "tact INTEGER NOT NULL" +
                "duration INTEGER NULL," +
                "extraInformations TEXT NULL);";
    }

    public String CreateConcertTableString()
    {
        return "create table concerts(id_Concert INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "songsDuration INTEGER NULL);";
    }

    public String CreateConcert_SongTableString()
    {
        return "create table concerts_songs" +
                "(FOREIGN KEY('id_Concert') REFERENCES concerts(id_Concert),"+
                "FOREIGN KEY('id_Song') REFERENCES songs(id_Song));";
    }

    public void addSong(Song song)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", song.getTitle());
        values.put("extraInformations", song.getExtraInformations());
        values.put("speedRate", song.getSpeedRate());
        values.put("tact", song.getMeasure());
        values.put("duration", song.getDuration());

        db.insert("songs", null, values);

        db.close();
    }

    public void deleteSong(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("songs", "id_Song = ?", new String[] { id });
        db.close();
    }

    public int updateSong(Song song)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", song.getTitle());
        values.put("extraInformations", song.getExtraInformations());
        values.put("speedRate", song.getSpeedRate());
        values.put("tact", song.getMeasure());

        values.put("duration", song.getDuration());

        int i = db.update("songs", values, "id_Song = ?", new String[]{String.valueOf(song.getId_Song())});

        db.close();

        return i;
    }

    public Song getSong(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("songs", //a. table name
                new String[] { "id_Song", "title", "tact", "extraInformations", "speedRate", "duration" }, // b. column names
                "id_Song = ?", // c. selections
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Song song = new Song(cursor.getString(1), cursor.getInt(4), cursor.getInt(2), cursor.getInt(5), cursor.getString(3));
        song.setId_Song(Integer.parseInt(cursor.getString(0)));

        return song;
    }

    public Cursor getAllSongs()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from songs", null);
    }


    public void addConcert(Concert concert)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
//        concert.computeSongsDuration();
//        values.put("name", concert.getName());
//        values.put("songsDuration", concert.getSongsDuration());

        db.insert("concerts", null, values);
        db.close();
    }

    public void deleteConcert(String id)
    {
        //TODO - delete concert MySQL
    }

    public int updateConcert(Concert concert)
    {
        //TODO - update concert MySQL

        return 0;
    }

    public Concert getConcert(int id)
    {
        //TODO - get concert MySQL

        return null;
    }

    public Cursor getAllConcerts()
    {
        //TODO - get all concerts MySQL

        return null;
    }



}
