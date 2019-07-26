package com.mbrigadoi.singthechart.presenter;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.mbrigadoi.singthechart.model.Chart;
import com.mbrigadoi.singthechart.model.Track;
import com.mbrigadoi.singthechart.model.utils.MxmApi;
import com.mbrigadoi.singthechart.view.LyricsActivity;
import com.mbrigadoi.singthechart.view.adapter.ChartAdapter;

import java.util.Locale;

public class ChartPresenterImpl implements ChartPresenter {
    private Chart mChart;

    @Override
    public void loadChart(String country) {
        mChart = MxmApi.getChart(country);
    }

    @Override
    public void bindViewHolder(ChartAdapter.ViewHolder holder, int position) {
        Track track = mChart.getTrack(position);

        holder.itemView.setTag(track);
        holder.coverArt.setImageBitmap(track.getCoverArt());
        holder.position.setText(String.format(Locale.US, "%d.", track.getPosition()));
        holder.name.setText(track.getName());
        holder.artist.setText(track.getArtist());
        holder.album.setText(track.getAlbum());
    }

    @Override
    public int getTracksCount() {
        return mChart.count();
    }

    @Override
    public void trackClick(Activity activity, int position) {
        Track track = mChart.getTrack(position);
        if(track.hasLyrics()) {
            Intent i = new Intent(activity, LyricsActivity.class);
            i.putExtra("cover", track.getCoverArt());
            i.putExtra("song", track.getName());
            i.putExtra("artist", track.getArtist());
            i.putExtra("album", track.getAlbum());
            i.putExtra("lyrics", track.getLyrics().getBody());
            activity.startActivity(i);
        } else {
            Toast.makeText(activity.getApplicationContext(),
                    "This track has no lyrics", Toast.LENGTH_SHORT).show();
        }
    }


}
