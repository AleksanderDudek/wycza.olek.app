package adudek.metronom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class AddConcert extends AppCompatActivity {


    private ArrayList<String> target;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_concert);

        //tutaj dodac zamaist new string to pobrane zespoly z bd

        //
        String[] values = new String[] {"Nirvana", "Aerosmith", "Lady Gaga", "Ewa Farna"};

        this.target = new ArrayList<>();
        this.target.addAll(Arrays.asList(values));

        this.adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.target);
        ListView listView = (ListView) findViewById(R.id.list_view_SongsToSelect);
        listView.setAdapter(this.adapter);
    }
}
