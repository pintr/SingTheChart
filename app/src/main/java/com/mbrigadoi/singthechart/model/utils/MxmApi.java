package com.mbrigadoi.singthechart.model.utils;

import com.mbrigadoi.singthechart.model.Chart;
import com.mbrigadoi.singthechart.model.Lyrics;
import com.mbrigadoi.singthechart.model.Track;

import org.json.JSONArray;
import org.json.JSONObject;

public class MxmApi {
    private static final String API_KEY = "1c9e075968d686e586bc8bee13b82ca0";
    private static final String BASE_URL = "https://api.musixmatch.com/ws/1.1/";

    public static Chart getChart(String country) {
        Chart chart = new Chart();
        String url = BASE_URL + "chart.tracks.get?apikey=" + API_KEY + "&country=" + country;
        String data = HttpsRequests.getString(url);
        if (data != null) {
            try {
                JSONArray tracks = (new JSONObject(data)).getJSONObject("message")
                        .getJSONObject("body")
                        .getJSONArray("track_list");
                JSONObject objTrack;
                Track track;

                for (int i = 0; i < tracks.length(); i++) {
                    objTrack = tracks.getJSONObject(i).getJSONObject("track");
                    track = new Track(
                        objTrack.getLong("track_id"),
                        i + 1,
                        objTrack.getString("track_name"),
                        objTrack.getString("artist_name"),
                        objTrack.getString("album_name")
                    );

                    track.setCoverArt(HttpsRequests.getBitmap(
                            objTrack.getString("album_coverart_350x350")));

                    track.setLyric(getLyrics(track.getId()));

                    chart.addTrack(track);
                }
            } catch (Exception e) {
                Debug.logStackTrace(e);
            }
        } else {
            chart = null;
        }
        return chart;
    }

    private static Lyrics getLyrics(long id) {
        Lyrics lyrics = new Lyrics();

        String url = BASE_URL + "track.lyrics.get?apikey=" + API_KEY + "&track_id=" + id;
        String data = HttpsRequests.getString(url);

        if (data != null) {
            try {
                JSONObject objLyrics = (new JSONObject(data)).getJSONObject("message")
                        .getJSONObject("body")
                        .getJSONObject("lyrics");

                lyrics.setId(objLyrics.getLong("lyrics_id"));
                lyrics.setBody(objLyrics.getString("lyrics_body"));
            } catch (Exception e) {
                Debug.logStackTrace(e);
            }
        } else {
            lyrics = new Lyrics(-1, null);
        }

        return lyrics;
    }
}
