package adudek.metronom;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Metronom extends AppCompatActivity {

    MetronomTask task;
    boolean buttonPressed = false;

    private static final int STEP = 1;
    private static final int MAX = 180;
    private static final int MIN = 60;
    private static final int TOTAL = MAX - MIN;

    @Override
    public void onBackPressed()
    {
        if(buttonPressed)
        {
            task.cancel(true);
            buttonPressed = false;
        }
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronom);
        Button button = (Button)findViewById(R.id.hit);

        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                EditText rate = (EditText) findViewById(R.id.editText_rate);
                EditText tact = (EditText) findViewById(R.id.editText_tact);

                Integer[] values = new Integer[2];
                values[0] = Integer.parseInt(rate.getText().toString());
                values[1] = Integer.parseInt(tact.getText().toString());

                if(!buttonPressed)
                {
                    task = new MetronomTask(getBaseContext());
                    task.execute(values);
                    buttonPressed = true;
                }
                else
                {
                    task.cancel(true);
                    buttonPressed = false;
                }
            }
        });

        SeekBar sb1 = (SeekBar) findViewById(R.id.seekBar);
        sb1.setMax(TOTAL);

        TextView tv = (TextView)findViewById(R.id.textView_seekBarRate);
        tv.setText(String.valueOf(sb1.getProgress()) + MIN);

        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                TextView tv = (TextView)findViewById(R.id.textView_seekBarRate);
                tv.setText(String.valueOf(seekBar.getProgress() + MIN));

                EditText tact = (EditText) findViewById(R.id.editText_tact);

                Integer[] values = new Integer[2];
                values[0] = Integer.parseInt(tv.getText().toString());
                values[1] = Integer.parseInt(tact.getText().toString());

                if(buttonPressed)
                {
                    task.cancel(true);
                    buttonPressed = false;
                }

                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){ }


                if(!buttonPressed)
                {
                    task = new MetronomTask(getBaseContext());
                    task.execute(values);
                    buttonPressed = true;
                }

            }
        });
    }
}

class MetronomTask extends AsyncTask<Integer,String,String>
{
    private Context mContext;
    MetronomClass metronom;
    MediaPlayer high;
    MediaPlayer low;

    public MetronomTask (Context context)
    {
        mContext = context;
    }



    @Override
    protected void onPreExecute() {
        // before executing background task. Like showing a progress dialog
        metronom = new MetronomClass(160,4);
        high = MediaPlayer.create(mContext, R.raw.high2);
        low = MediaPlayer.create(mContext, R.raw.low2);
    }

    @Override
    protected String doInBackground(Integer... params)
    {
        metronom.setBpm(params[0]);
        metronom.setMeasure(params[1]);


        metronom.start();

        while (metronom.isLaunched())
        {

            if (isCancelled())
                break;

            try
            {
                Thread.sleep((long) (1000 * (60 / metronom.getBpm())));
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            int c = metronom.getCounter();
            metronom.setCounter(++c);

            if (metronom.getCounter() % metronom.getMeasure() == 0)
            {
                high.start();
            } else
            {
                low.start();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        metronom.stop();

    }

}
