package com.expd.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Track {
    private int id;
    private String title;
    private String album;
    private Set<Artist> artists = new HashSet<>();
    private LocalDate issueDate;
    private int duration;
    private MediaType mediaType;

    public Track(){}

    public Track(String title, String album, Set<Artist> artists, LocalDate issueDate, int duration, MediaType mediaType) {
        this.title = title;
        this.album = album;
        this.artists = artists;
        this.issueDate = issueDate;
        this.duration = duration;
        this.mediaType = mediaType;
    }

    public Track(String title, String album, LocalDate issueDate, int duration, MediaType mediaType) {
        this.title = title;
        this.album = album;
        this.issueDate = issueDate;
        this.duration = duration;
        this.mediaType = mediaType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artists=" + artists +
                ", issueDate=" + issueDate +
                ", duration=" + duration +
                ", mediaType=" + mediaType +
                '}';
    }
}
