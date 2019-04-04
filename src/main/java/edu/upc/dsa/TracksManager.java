package edu.upc.dsa;

import edu.upc.dsa.models.Album;
import edu.upc.dsa.models.Author;
import edu.upc.dsa.models.Track;

import java.util.List;

public interface TracksManager {

    /**
     * Add a new edu.upc.dsa.models.Author
     * @param name author name
     * @return author identifier
     */
    String addAuthor(String name);

    /**
     * Add a new edu.upc.dsa.models.Album
     * @param name album name
     * @param singer album singer
     * @param year album year
     * @param idAuthor author identifier
     * @throws AuthorNotFoundException if author does not exist
     * @return album identifier
     */
    String addAlbum(String name, String singer, int year, String idAuthor) throws AuthorNotFoundException;

    /**
     * Add a new edu.upc.dsa.models.Track
     * @param title track title
     * @param singer track singer
     * @param idAlbum album identifier
     * @param idAuthor author identifier
     * @throws AlbumNotFoundException if album does not exist
     * @throws AuthorNotFoundException if author does not exist
     * @return track identifier
     */
    String addTrack(String title, String singer, String idAlbum, String idAuthor) throws AuthorNotFoundException, AlbumNotFoundException;

    /**
     * Get a track by id
     * @param id track identifier
     * @return track object
     * @throws TrackNotFoundException if track does not exist
     */
    Track getTrack(String id) throws TrackNotFoundException;

    /**
     * Get an album by id
     * @param id album identifier
     * @return album object
     * @throws AlbumNotFoundException if album does not exist
     */
    Album getAlbum(String id) throws AlbumNotFoundException;

    /**
     * Get an author by id
     * @param id author identifier
     * @return author object
     * @throws AuthorNotFoundException if author does not exist
     */
    Author getAuthor(String id) throws AuthorNotFoundException;

    /**
     * Get all tracks
     * @return list of tracks
     */
    List<Track> getTracks();

    /**
     * Get all albums
     * @return list of albums
     */
    List<Album> getAlbums();

    /**
     * Get all authors
     * @return list of authors
     */
    List<Author> gerAuthors();

    /**
     * Update a track
     * @param track track object
     * @throws TrackNotFoundException if track does not exist
     */
    void updateTrack(Track track) throws TrackNotFoundException;

    /**
     * Update an album
     * @param album album object
     * @throws AlbumNotFoundException if album does not exist
     */
    void updateAlbum(Album album) throws AlbumNotFoundException;

    /**
     * Update an author
     * @param author author object
     * @throws AuthorNotFoundException if author does not exist
     */
    void updateAuthor(Author author) throws AuthorNotFoundException;

    /**
     * Delete a track by id
     * @param id track identifier
     * @throws TrackNotFoundException if track does not exist
     */
    void deleteTrack(String id) throws TrackNotFoundException;

    /**
     * Delete an album by id
     * @param id album identifier
     * @throws AlbumNotFoundException if album does not exist
     */
    void deleteAlbum(String id) throws AlbumNotFoundException;

    /**
     * Delete an author by id
     * @param id author identifier
     * @throws AuthorNotFoundException if author does not exist
     */
    void deleteAuthor(String id) throws AuthorNotFoundException;

    /**
     * Get the number of tracks
     * @return number of tracks
     */
    int numTracks();

    /**
     * Get the number of albums
     * @return number of albums
     */
    int numAlbums();

    /**
     * Get the number of authors
     * @return number of authors
     */
    int numAuthors();

    /**
     * Clear data structures
     */
    void clear();
}
