package adudek.metronom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mateusz on 23.05.2017.
 */

public class songrow_Adapter extends BaseAdapter
{
    Context context;
    ArrayList<Song> songs;
    private static LayoutInflater inflater = null;

    public songrow_Adapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.song_row, null);

        TextView header = (TextView) vi.findViewById(R.id.header_row);
        TextView extraInfo = (TextView) vi.findViewById(R.id.extraInfo_row);
        TextView speedrate = (TextView) vi.findViewById(R.id.speedRate_row);
        TextView tact = (TextView) vi.findViewById(R.id.tact_row);
        TextView duration = (TextView) vi.findViewById(R.id.duration_row);

        header.setText(songs.get(position).getTitle());
        speedrate.setText(String.valueOf(songs.get(position).getSpeedRate()));
        tact.setText(String.valueOf(songs.get(position).getMeasure()));
        duration.setText(String.valueOf(songs.get(position).getDuration()) + " sekund");
        extraInfo.setText(songs.get(position).getExtraInformations());

        return vi;
    }
}
