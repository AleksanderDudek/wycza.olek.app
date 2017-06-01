package adudek.metronom;

import java.io.Serializable;

/**
 * Created by Mateusz on 08.05.2017.
 */

public class Song implements Serializable
{
    private String title;
    private String extraInformations;
    private int speedRate; // for example 180
    private int measure; // for example 2
    private int duration; // in seconds
    private int id_Song;

    public static int idNumber = 1;

    //jesli istnieje baza danych, to chce wiedziec jaki jest count dla tabeli piosenek i to +1 ustawiam jako wartosc id
    //inaczej po prostu wpisuje 1
//    private static int idNumber = DatabaseHandler.dbExists() ? DatabaseHandler.getSongCount()+1 : 1;

    // generalnie id przy kazdym dzialaniu konstruktora powieksza sie o 1, pytanie, czy jak
    // nastepnym razem odpale apke, to id znowu bedzie 1 czy juz ma baze danych

    public Song() {}

    public Song(String title, int speedRate,int measure)
    {
        super();
        this.id_Song = idNumber;
        this.title = title;
        this.speedRate = speedRate;
        this.measure = measure;
        idNumber++;
    }

    public Song(String title, int speedRate,int measure, int duration)
    {
        super();
        this.id_Song = idNumber;
        this.title = title;
        this.speedRate = speedRate;
        this.duration = duration;
        this.measure = measure;
        idNumber++;
    }

    public Song(String title, int speedRate, int measure, String extraInformations)
    {
        super();
        this.id_Song = idNumber;
        this.title = title;
        this.speedRate = speedRate;
        this.extraInformations = extraInformations;
        this.measure = measure;
        idNumber++;
    }

    public Song(String title, int speedRate, int measure, int duration, String extraInformations)
    {
        super();
        this.id_Song = idNumber;
        this.title = title;
        this.speedRate = speedRate;
        this.duration = duration;
        this.measure = measure;
        this.extraInformations = extraInformations;
        idNumber++;
    }

    //constructor for db sake
    public Song(String id, String title, String speedRate, String measure, String duration, String extraInformations)
    {
        super();
        this.id_Song = Integer.parseInt(id);
        this.title = title;
        this.extraInformations = extraInformations;
        this.speedRate = Integer.parseInt(speedRate);
        this.duration = Integer.parseInt(duration);
        this.measure = Integer.parseInt(measure);
    }

    @Override
    public String toString()
    {
        return "Piosenka: [id="+ this.id_Song + ", tytuł=" + this.title + ", prędkość=" + this.speedRate + ", takt=" + this.measure + ", czas=" + this.duration
                + ", informacje=" + this.extraInformations + "]";
    }


    public void setId_Song(int id_Song)
    {
        this.id_Song = id_Song;
    }

    public int getId_Song()
    {
        return id_Song;
    }

    public int getSpeedRate()
    {
        return speedRate;
    }

    public void setSpeedRate(int speedRate)
    {
        this.speedRate = speedRate;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getExtraInformations()
    {
        return extraInformations;
    }

    public void setExtraInformations(String extraInformations)
    {
        this.extraInformations = extraInformations;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public int getMeasure()
    {
        return measure;
    }

    public void setMeasure(int measure)
    {
        this.measure = measure;
    }
}
