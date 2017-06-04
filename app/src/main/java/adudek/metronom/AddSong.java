package adudek.metronom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddSong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
    }

    public void AddSong(View view)
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
        db.addSong(song);
        db.close();
        //tutaj koniec

        //tworze intent wskazujacy na ten intent
        Intent intent = new Intent(this, SongsList.class);

        //dolacza nasza piosenke do tego intentu pod nazwa "new_song"
        intent.putExtra("new_song", song);

        //to zwraca intent i ustawia jego status
        setResult(RESULT_OK, intent);

//        startActivityForResult(intent, RESULT_OK);
//        startActivity(intent);
        finish();
    }

}
