package edu.upc.dsa;

import edu.upc.dsa.models.Album;
import edu.upc.dsa.models.Author;
import edu.upc.dsa.models.Track;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TracksManagerImpl implements TracksManager {

    //Logger
    private final static Logger logger = Logger.getLogger(TracksManagerImpl.class);

    //Facade
    private static TracksManager instance;
    private HashMap<String, Author> authors;
    private HashMap<String, Album> albums;
    private HashMap<String, Track> tracks;

    //Private constructor
    private TracksManagerImpl() {
        this.albums = new HashMap<>();
        this.tracks = new HashMap<>();
    }

    //getInstance Method
    public static TracksManager getInstance() {
        if (instance == null) instance = new TracksManagerImpl();
        return instance;
    }

    @Override
    public String addAuthor(String name) {
        Author author = new Author(name);
        this.authors.put(author.getId(), author);
        logger.info("Author added");
        return author.getId();
    }

    //Methods
    @Override
    public String addAlbum(String name, String singer, int year, String idAuthor) throws AuthorNotFoundException {
        Author author = this.getAuthor(idAuthor);
        Album album = new Album(name, singer, year);
        author.addAlbum(album);
        this.albums.put(album.getId(), album);
        logger.info("Album added");
        return album.getId();
    }

    @Override
    public String addTrack(String title, String singer, String idAlbum, String idAuthor) throws AuthorNotFoundException, AlbumNotFoundException {
        Author author = this.getAuthor(idAuthor);
        Album album = this.getAlbum(idAlbum);
        Track track = new Track(title, singer);
        author.addTrack(track);
        album.addTrack(track);
        this.tracks.put(track.getId(), track);
        logger.info("Track added");
        return track.getId();
    }

    @Override
    public Track getTrack(String id) throws TrackNotFoundException {
        Track track = this.tracks.get(id);
        if (track != null) return track;
        else throw new TrackNotFoundException();
    }

    @Override
    public Album getAlbum(String id) throws AlbumNotFoundException {
        Album album = this.albums.get(id);
        if (album != null) return album;
        else throw new AlbumNotFoundException();
    }

    @Override
    public Author getAuthor(String id) throws AuthorNotFoundException {
        Author author = this.authors.get(id);
        if (author != null) return author;
        else throw new AuthorNotFoundException();
    }

    @Override
    public List<Track> getTracks() {
        return new ArrayList<>(this.tracks.values());
    }

    @Override
    public List<Album> getAlbums() {
        return new ArrayList<>(this.albums.values());
    }

    @Override
    public List<Author> gerAuthors() {
        return new ArrayList<>(this.authors.values());
    }

    @Override
    public void updateTrack(Track track) throws TrackNotFoundException {
        this.getTrack(track.getId());
        this.tracks.put(track.getId(), track);
        logger.info("Track updated");
    }

    @Override
    public void updateAlbum(Album album) throws AlbumNotFoundException {
        this.getAlbum(album.getId());
        this.albums.put(album.getId(), album);
        logger.info("Album updated");
    }

    @Override
    public void updateAuthor(Author author) throws AuthorNotFoundException {
        this.getAuthor(author.getId());
        this.authors.put(author.getId(), author);
        logger.info("Author updated");
    }

    @Override
    public void deleteTrack(String id) throws TrackNotFoundException {
        this.getTrack(id);
        this.tracks.remove(id);
        logger.info("Track deleted");
    }

    @Override
    public void deleteAlbum(String id) throws AlbumNotFoundException {
        this.getAlbum(id);
        this.albums.remove(id);
        logger.info("Album deleted");
    }

    @Override
    public void deleteAuthor(String id) throws AuthorNotFoundException {
        this.getAuthor(id);
        this.authors.remove(id);
        logger.info("Author deleted");
    }

    @Override
    public int numTracks() {
        logger.info("Number of tracks: " + this.tracks.size());
        return this.tracks.size();
    }

    @Override
    public int numAlbums() {
        logger.info("Number of albums: " + this.albums.size());
        return this.albums.size();
    }

    @Override
    public int numAuthors() {
        logger.info("Number of authors: " + this.authors.size());
        return this.authors.size();
    }

    @Override
    public void clear() {
        this.albums = new HashMap<>();
        this.tracks = new HashMap<>();
        this.authors = new HashMap<>();
    }
}