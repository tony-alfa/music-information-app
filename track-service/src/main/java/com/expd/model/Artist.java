package com.expd.model;

public class Artist {
    private int id;
    private String name;
    private String nationality;

    private Genre genre;

    public Artist(){}

    public Artist(int id, String name, String nationality, Genre genre) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.genre = genre;
    }
    public Artist(String name, String nationality, Genre genre) {
        this.name = name;
        this.nationality = nationality;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", genre=" + genre +
                '}';
    }
}
