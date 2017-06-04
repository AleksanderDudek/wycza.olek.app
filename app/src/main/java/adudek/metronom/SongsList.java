package adudek.metronom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;

public class SongsList extends AppCompatActivity {

    private ArrayList<Song> songs;
    private songrow_Adapter adapter;
    ListView listView;
    private static final String TAG = "EntityListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);

        //tutaj call do bazy danych

        //
        songs = new ArrayList<>();
//        songs.add(new Song("Smoke on the water", 140, 5, 130, "Dym na wodzie? WTF"));
//        songs.add(new Song("Smells like teen spirit", 160, 4, 150, "Fajnie posłuchać w aucie"));
//        songs.add(new Song("Nothing else matters", 180, 3, 195, "Piosenka Metallicy"));
        //dla kazdego rekordu z bazy tutaj songs.add te wartosc zrzutowana na odpowiedni typ
        //
        DatabaseHandler db = new DatabaseHandler(this);
        songs.addAll(db.getAllSongs());
        db.close();
        listView = (ListView) findViewById(R.id.list_view_Songs);
        adapter = new songrow_Adapter(this, songs);
        listView.setAdapter(adapter);

        Log.i(TAG, "before listener");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                // use position to find your values
                // to go to ShowDetailsActivity, you have to use Intent
                Log.i(TAG, "inside listener");

                Intent detailScreen = new Intent(SongsList.this, EditSong.class);
                detailScreen.putExtra("position", String.valueOf(listView.getItemIdAtPosition(position))  ); // pass value if needed
                startActivityForResult(detailScreen, 2);
            }
        });
    }

    public void OpenAddSongActivity(View view)
    {
        Intent intent = new Intent(this, AddSong.class);
        startActivityForResult(intent, 1);
    }
//    public void EditSongActivity(View view)
//    {
//        Intent intent = new Intent(this, EditSong.class);
//        startActivityForResult(intent, 1);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.e("CALLED", "OnActivity Result");


        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Song newSong = (Song)extras.get("new_song");
            songs.add(newSong);
            adapter.notifyDataSetChanged();
            //
        }
        if (requestCode == 2 && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Song newSong = (Song)extras.get("new_song");
            int index = Integer.parseInt(extras.get("index").toString());
            songs.set(index, newSong); //zmienia wartosc
            adapter.notifyDataSetChanged();
            //
        }
    }


}
