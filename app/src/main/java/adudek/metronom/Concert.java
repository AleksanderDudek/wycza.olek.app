package adudek.metronom;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mateusz on 08.05.2017.
 */

public class Concert implements Serializable
{
//    private ArrayList<Song> listOfSongs;


    private int id_Concert;
//    private int songId;
//    private int songsDuration; // in seconds
    private static int idNumber =  1;
    private String name;
    private String listOfSongIds;

    public String getListOfSongIds() {
        return listOfSongIds;
    }

    public void setListOfSongIds(String listOfSongIds) {
        this.listOfSongIds = listOfSongIds;
    }


    public Concert() {}

    public Concert(String name)
    {
        this.name = name;
        this.id_Concert = idNumber;
        idNumber++;
    }
    public Concert(String name, String listOfIds)
    {
        this.name = name;
        this.listOfSongIds = listOfIds;
        this.id_Concert = idNumber;
        idNumber++;
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
