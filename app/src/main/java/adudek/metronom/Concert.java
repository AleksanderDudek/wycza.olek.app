package adudek.metronom;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mateusz on 08.05.2017.
 */

public class Concert implements Serializable
{
    private ArrayList<Song> listOfSongs;
    private String name;
    private int id_Concert;
    private int songsDuration; // in seconds
    private static int idNumber = 1;

    public Concert() {}

    public Concert(String name)
    {
        this.name = name;
        this.id_Concert = idNumber;
        idNumber++;
        listOfSongs = new ArrayList<>();
    }

    public void computeSongsDuration()
    {
        songsDuration = 0;
        for (Song song: listOfSongs)
        {
            songsDuration += song.getDuration();
        }
    }

    public int getSongsDuration()
    {
        return songsDuration;
    }

    public static int getIdNumber()
    {
        return idNumber;
    }

    public ArrayList<Song> getListOfSongs()
    {
        return listOfSongs;
    }

    public void setListOfSongs(ArrayList<Song> listOfSongs)
    {
        this.listOfSongs = listOfSongs;
    }

    public void addSongToList(Song song)
    {
        this.listOfSongs.add(song);
        this.songsDuration += song.getDuration();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId_Concert()
    {
        return id_Concert;
    }

    public void setId_Concert(int id_Concert)
    {
        this.id_Concert = id_Concert;
    }


}
