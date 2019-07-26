package com.mbrigadoi.singthechart.model;

import java.util.ArrayList;
import java.util.List;

public class Chart {
    private List<Track> mTracks = new ArrayList<>();

    public Chart() {}

    public Track getTrack(int index) {
        return mTracks.get(index);
    }

    public void addTrack(Track track) {
        mTracks.add(track);
    }

    public int count() {
        return mTracks.size();
    }

    @Override
    public String toString() {
        return "Chart{" +
                "mTracks=" + mTracks.toString() +
                '}';
    }
}
