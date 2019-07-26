package com.mbrigadoi.singthechart.model;

import android.graphics.Bitmap;

public class Track {
    private long mId;
    private int mPosition;
    private String mName;
    private String mArtist;
    private String mAlbum;
    private Bitmap mCoverArt;
    private Lyrics mLyrics;

    private Track(long id, int position, String name, String artist, String album, Bitmap coverArt, Lyrics lyrics) {
        this.mId = id;
        this.mPosition = position;
        this.mName = name;
        this.mArtist = artist;
        this.mAlbum = album;
        this.mCoverArt = coverArt;
        this.mLyrics = lyrics;
    }

    public Track(long id, int position, String name, String artist, String album) {
        this(id, position, name, artist, album, null, null);
    }

    public long getId() {
        return mId;
    }

    public int getPosition() {
        return mPosition;
    }

    public String getName() {
        return mName;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public Bitmap getCoverArt() {
        return mCoverArt;
    }

    public Lyrics getLyrics() {
        return mLyrics;
    }

    public void setCoverArt(Bitmap coverArt) {
        mCoverArt = coverArt;
    }

    public void setLyric(Lyrics lyrics) {
        mLyrics = lyrics;
    }

    public boolean hasLyrics() {
        return mLyrics.getBody() != null && mLyrics.getId() != -1;
    }

    @Override
    public String toString() {
        return "Track{" +
                "mId=" + mId +
                ", mPosition=" + mPosition +
                ", mName='" + mName + '\'' +
                ", mArtist='" + mArtist + '\'' +
                ", mAlbum='" + mAlbum + '\'' +
                ", mCoverArt=" + mCoverArt +
                ", mLyrics=" + mLyrics +
                '}';
    }
}
