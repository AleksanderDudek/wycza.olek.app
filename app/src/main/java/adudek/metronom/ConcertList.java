package adudek.metronom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ConcertList extends AppCompatActivity {

    private ArrayList<String> target;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_list);

        String[] values = new String[] {"Koncert w Policach", "Koncert w Hormonie", "Próby w piątek"};

        this.target = new ArrayList<>();
        this.target.addAll(Arrays.asList(values));

        this.adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.target);
        ListView listView = (ListView) findViewById(R.id.list_view_Concerts);
        listView.setAdapter(this.adapter);
    }

    public void OpenAddConcertActivity(View view)
    {
        Intent intent = new Intent(this, AddConcert.class);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.e("CALLED", "OnActivity Result");


        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Concert newconcert = (Concert)extras.get("new_concert");
            target.add(newconcert.getName());
            adapter.notifyDataSetChanged();
            //
        }

    }
}
