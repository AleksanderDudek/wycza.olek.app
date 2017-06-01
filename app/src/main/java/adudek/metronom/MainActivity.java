package adudek.metronom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openListSongs(View view)
    {
        Intent intent = new Intent(this, SongsList.class);
        startActivity(intent);
    }

    public void openConcertLists(View view)
    {
        Intent intent = new Intent(this, ConcertList.class);
        startActivity(intent);
    }

    public void openMetronom(View view)
    {
        Intent intent = new Intent(this, Metronom.class);
        startActivity(intent);
    }
}
