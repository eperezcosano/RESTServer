package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.LinkedList;

public class Author {

    //Attributes
    private String id;
    private String name;
    private LinkedList<Track> tracks;
    private LinkedList<Album> albums;

    //Constructors
    public Author() {
        this.id = RandomUtils.getId();
    }

    public Author(String name) {
        this();
        this.name = name;
        this.tracks = new LinkedList<>();
        this.albums = new LinkedList<>();
    }

    //Methods
    public void addTrack(Track track) {
        this.tracks.add(track);
    }

    public void addAlbum(Album album) {
        this.albums.add(album);
    }

    //Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(LinkedList<Track> tracks) {
        this.tracks = tracks;
    }

    public LinkedList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(LinkedList<Album> albums) {
        this.albums = albums;
    }
}
