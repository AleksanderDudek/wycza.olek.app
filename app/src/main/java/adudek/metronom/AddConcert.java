package adudek.metronom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class AddConcert extends AppCompatActivity {

    private static final String TAG = "EntityListActivity";

    private ArrayList<String> target;
    private ArrayAdapter adapter, adapterTwo;
    ListView listView;
    ListView addHere;
    private ArrayList<String> toBeAdded;
    private DatabaseHandler handler;
    ArrayList<Song> songsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_concert);

        //tutaj dodac zamaist new string to pobrane zespoly z bd
        DatabaseHandler db = new DatabaseHandler(this);
        songsList = db.getAllSongs();
        db.close();
        //to dodaje tytuly do listy
        final ArrayList<String> values = new ArrayList<String> () {};

        for (int i = 0; i< songsList.size(); i++)
        {
            values.add(songsList.get(i).getTitle());
        }

        //to dodaje do listy wszystkie wartosci
        this.target = new ArrayList<>();
        this.target.addAll(values);


        this.adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.target);

        //to jest ten listview po lwej a ten drugi to list_view_SelectedSongs
        listView = (ListView) findViewById(R.id.list_view_SongsToSelect);
        listView.setAdapter(this.adapter);
///tu juz jest ustawiona ta lista

        this.toBeAdded = new ArrayList<> ();

        this.adapterTwo = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.toBeAdded);

        addHere = (ListView) findViewById(R.id.list_view_SelectedSongs);
        addHere.setAdapter(this.adapterTwo);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                // use position to find your values
                // to go to ShowDetailsActivity, you have to use Intent
                Log.i(TAG, "inside listener of concert");
                //problem real time update
                //zwraca nazwe
                adapterTwo.add(target.get(position));

            }
        });
    }

    public void AddConBtn(View view) {
        //pobierz tytul koncertu
        EditText title = (EditText) findViewById(R.id.editText_ConcertTitle);
        //pobierz liste piosenek
        ListView selected = (ListView) findViewById(R.id.list_view_SelectedSongs);
        //wynajdz piosenki o tytulach

        String parsedIds = new String();

        for (int i = 0; i < selected.getAdapter().getCount(); i++)
        {
            parsedIds.concat(selected.getAdapter().getItem(i).toString());
            if(i != (selected.getAdapter().getCount()-1)) {
                parsedIds.concat(",");
            }

        }

        Log.i(TAG, "done " + parsedIds);
        //pobierz z tabeli songs

        //dodaj kazdy rekord jako id koncertu, nazwa koncertu, id piosenki

        Concert concert = new Concert(title.getText().toString(), parsedIds );
        //tworze intent wskazujacy na ten intent
        Intent intent = new Intent(this, ConcertList.class);

        //dolacza nasza piosenke do tego intentu pod nazwa "new_song"
        intent.putExtra("new_concert", concert);

        //to zwraca intent i ustawia jego status
        setResult(RESULT_OK, intent);

//        startActivityForResult(intent, RESULT_OK);
//        startActivity(intent);
        finish();
    }
}
