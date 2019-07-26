package com.mbrigadoi.singthechart.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbrigadoi.singthechart.R;

public class LyricsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);
        ImageView mCover = findViewById(R.id.image_cover_album);
        TextView mSong = findViewById(R.id.text_song_name);
        TextView mArtist = findViewById(R.id.text_artist_name);
        TextView mAlbum = findViewById(R.id.text_album_name);
        TextView mLyrics = findViewById(R.id.text_lyrics);

        Intent mIntent = getIntent();

        mCover.setImageBitmap((Bitmap) mIntent.getParcelableExtra("cover"));
        mSong.setText(mIntent.getStringExtra("song"));
        mArtist.setText(mIntent.getStringExtra("artist"));
        mAlbum.setText(mIntent.getStringExtra("album"));
        mLyrics.setText(mIntent.getStringExtra("lyrics"));

        mLyrics.setMovementMethod(new ScrollingMovementMethod());
    }
}
