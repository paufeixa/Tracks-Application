package edu.upc.dsa.tracks.domain;

import java.io.Serializable;

public class Track implements Serializable {

    String id;
    String title;
    String singer;

    public Track() {

    }

    public Track(String id, String title, String singer) {
        this.id = id;
        this.title = title;
        this.singer = singer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "Track [id=" + id + ", title=" + title + ", singer=" + singer + "]";
    }
}