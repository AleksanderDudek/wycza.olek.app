package adudek.metronom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
/**
 * Created by a on 2017-06-01.
 */

public class EditSong extends AppCompatActivity{
    private static final String TAG = "EntityListActivity";

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song); //tworzy view edit
        Log.i(TAG, "inside listener");

        Bundle bundle = getIntent().getExtras();

        Log.i(TAG, "before getdb and pos: " + bundle.get("position").toString() );

        DatabaseHandler db = new DatabaseHandler(this);
        //tutaj zasysam z bazy danych song o id pobranym z listy, tyle, ze to zaklada, ze ID
        //piosenki jest tozsame z pozycja na liscie? moze byc fuck up

        //id pobierane z hashsetu i parsowane do inta, potem pobieranie z db
        Log.i(TAG, "before getsong");

        Song editable =  db.getSong(Integer.parseInt(bundle.get("position").toString()));

        Log.i(TAG, "after getsong");

        db.close();
        EditText title = (EditText) findViewById(R.id.editText_SongTitle);
        EditText extraInfo = (EditText) findViewById(R.id.editText_SongInformation);
        EditText rate = (EditText) findViewById(R.id.editText_SongRate);
        EditText duration = (EditText) findViewById(R.id.editText_SongDuration);
        EditText measure = (EditText) findViewById(R.id.editText_tact);

        Log.i(TAG, "before get song id ");

        index = editable.getId_Song();
        Log.i(TAG, "song id " + index + " " + editable.toString());

        title.setText(editable.getTitle());
        extraInfo.setText(editable.getExtraInformations());
        rate.setText(String.valueOf(editable.getSpeedRate()));
        duration.setText(String.valueOf(editable.getDuration()));
        measure.setText(String.valueOf(editable.getMeasure()));

        //widok ustawiony wartoscia z db
    }

    //after click
    public void EditSong(View view)
    {
        //tutaj pobieram dane tekstowe z widoku
        EditText title = (EditText) findViewById(R.id.editText_SongTitle);
        EditText extraInfo = (EditText) findViewById(R.id.editText_SongInformation);
        EditText rate = (EditText) findViewById(R.id.editText_SongRate);
        EditText duration = (EditText) findViewById(R.id.editText_SongDuration);
        EditText measure = (EditText) findViewById(R.id.editText_tact);

        //tutaj tworze nowa piosenke
        Song song = new Song(title.getText().toString(), Integer.parseInt(rate.getText().toString()),Integer.parseInt(measure.getText().toString()), Integer.parseInt(duration.getText().toString()), extraInfo.getText().toString());

        //tutaj mozna otworzyc polaczenie z baza danych i zapis
        DatabaseHandler db = new DatabaseHandler(this);
        db.updateSong(song);
        db.close();
        //tutaj koniec

        //tworze intent wskazujacy na ten intent
        Intent intent = new Intent(EditSong.this, SongsList.class);

        //dolacza nasza piosenke do tego intentu pod nazwa "new_song"
        intent.putExtra("new_song", song);
        intent.putExtra("index", index);

        //to zwraca intent i ustawia jego status
        setResult(RESULT_OK, intent);

//        startActivityForResult(intent, RESULT_OK);
//        startActivity(intent);
        finish();
    }
}
