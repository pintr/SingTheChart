package com.mbrigadoi.singthechart.model;

public class Lyrics {
    private long mId;
    private String mBody;

    public Lyrics() {}

    public Lyrics(int id, String body) {
        this.mId = id;
        this.mBody = body;
    }

    public long getId() {
        return mId;
    }

    public String getBody() {
        return mBody;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public void setBody(String body) {
        this.mBody = body;
    }

    @Override
    public String toString() {
        return "Lyrics{" +
                "mId=" + mId +
                ", mBody='" + mBody + '\'' +
                '}';
    }
}
