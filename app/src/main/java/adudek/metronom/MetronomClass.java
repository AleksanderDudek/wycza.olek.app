package adudek.metronom;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

/**
 * Created by Mateusz on 22.05.2017.
 */

public class MetronomClass
{
    private double bpm;
    private int measure, counter;
    private boolean launched;

    public MetronomClass(double bpm, int measure) {
        this.bpm = bpm;
        this.measure = measure;
    }

    public boolean isLaunched()
    {
        return launched;
    }

    public void setLaunched(boolean launched)
    {
        this.launched = launched;
    }

    public double getBpm()
    {
        return bpm;
    }

    public void setBpm(double bpm)
    {
        this.bpm = bpm;
    }

    public int getMeasure()
    {
        return measure;
    }

    public void setMeasure(int measure)
    {
        this.measure = measure;
    }

    public int getCounter()
    {
        return counter;
    }

    public void setCounter(int counter)
    {
        this.counter = counter;
    }

    public void start()
    {
        this.launched = true;

//        final MediaPlayer high = MediaPlayer.create(context, R.raw.high2);
//        final MediaPlayer low = MediaPlayer.create(context, R.raw.low2);
//
//        while (launched)
//        {
//            try
//            {
//                Thread.sleep((long) (1000 * (60 / bpm)));
//            }
//            catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//
//            counter++;
//
//            if (counter % measure == 0)
//            {
//                high.start();
//                //System.out.println("RAZ!");
////                Lab5.playSound("res/raw/high2.wav ");
//            } else
//            {
//                //System.out.println("*");
//                low.start();
////                Lab5.playSound("res/raw/low2.wav ");
//            }
//        }
    }

    public void stop()
    {
        this.launched = false;
    }
}
