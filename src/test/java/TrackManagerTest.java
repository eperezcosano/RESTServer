import edu.upc.dsa.*;
import edu.upc.dsa.models.Album;
import edu.upc.dsa.models.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TrackManagerTest {

    private TracksManager tm;

    @Before
    public void setUp() throws AlbumNotFoundException {
        this.tm = TracksManagerImpl.getInstance();

        String idAlbum1 = this.tm.addAlbum("Para Todos Los Publicos", "Georgie Dann", 2001);
        this.tm.addTrack("La Barbacoa", "Georgie Dann", idAlbum1);
        this.tm.addTrack("Carnaval, Carnaval", "Georgie Dann", idAlbum1);

        String idAlbum2 = this.tm.addAlbum("Vida", "Luis Fonsi", 2019);
        this.tm.addTrack("Despacito", "Luis Fonsi", idAlbum2);

        String idAlbum3 = this.tm.addAlbum("Metallica", "Metallica", 1991);
        this.tm.addTrack("Enter Sandman", "Metallica", idAlbum3);
    }

    @After
    public void tearDown() {
        this.tm.clear();
    }

    @Test
    public void testAddAlbum() {
        this.tm.addAlbum("Album", "Singer", 2000);
        Assert.assertEquals(4, this.tm.numAlbums());
    }

    @Test
    public void testAddTrack() throws AlbumNotFoundException {
        String idAlbum = this.tm.addAlbum("Album", "Singer", 2000);
        this.tm.addTrack("Title", "Singer", idAlbum);
        Assert.assertEquals(5, this.tm.numTracks());
    }

    @Test(expected = AlbumNotFoundException.class)
    public void testAddTrackAndAlbumNotFound() throws AlbumNotFoundException {
        this.tm.addTrack("Title", "Singer", null);
    }

    @Test
    public void testGetTrack() throws AlbumNotFoundException, TrackNotFoundException {
        String idAlbum = this.tm.addAlbum("Album", "Singer", 2000);
        String idTrack = this.tm.addTrack("Title", "Singer", idAlbum);

        Assert.assertEquals("Title", this.tm.getTrack(idTrack).getTitle());
        Assert.assertEquals("Singer", this.tm.getTrack(idTrack).getSinger());
    }

    @Test(expected = TrackNotFoundException.class)
    public void testGetTrackAndTrackNotFound() throws TrackNotFoundException {
        this.tm.getTrack(null);
    }

    @Test
    public void testGetAlbum() throws AlbumNotFoundException {
        String idAlbum = this.tm.addAlbum("Album", "Singer", 2000);

        Assert.assertEquals("Album", this.tm.getAlbum(idAlbum).getName());
        Assert.assertEquals("Singer", this.tm.getAlbum(idAlbum).getSinger());
        Assert.assertEquals(2000, this.tm.getAlbum(idAlbum).getYear());
    }

    @Test(expected = AlbumNotFoundException.class)
    public void testGetAlbumAndAlbumNotFound() throws AlbumNotFoundException {
        this.tm.getAlbum(null);
    }

    @Test
    public void testGetTracks() {
        List<Track> tracks = this.tm.getTracks();

        Assert.assertEquals(4, tracks.size());
    }

    @Test
    public void testGetAlbums() {
        List<Album> albums = this.tm.getAlbums();

        Assert.assertEquals(3, albums.size());
    }

    @Test
    public void testUpdateTrack() throws AlbumNotFoundException, TrackNotFoundException {
        String idAlbum = this.tm.addAlbum("Album", "Singer", 2000);
        Track track = new Track("Title", "Singer");
        String idTrack = this.tm.addTrack(track.getTitle(), track.getSinger(), idAlbum);

        Assert.assertEquals("Title", this.tm.getTrack(idTrack).getTitle());
        Assert.assertEquals("Singer", this.tm.getTrack(idTrack).getSinger());

        track.setId(idTrack);
        track.setTitle("Title2");
        track.setSinger("Singer2");
        this.tm.updateTrack(track);

        Assert.assertEquals("Title2", this.tm.getTrack(idTrack).getTitle());
        Assert.assertEquals("Singer2", this.tm.getTrack(idTrack).getSinger());
    }

    @Test(expected = TrackNotFoundException.class)
    public void testUpdateTrackAndTrackNotFound() throws TrackNotFoundException {
        this.tm.updateTrack(new Track("Title", "Singer"));
    }

    @Test
    public void testUpdateAlbum() throws AlbumNotFoundException {
        Album album = new Album("Name", "Singer", 2000);
        String idAlbum = this.tm.addAlbum(album.getName(), album.getSinger(), album.getYear());

        Assert.assertEquals("Name", this.tm.getAlbum(idAlbum).getName());
        Assert.assertEquals("Singer", this.tm.getAlbum(idAlbum).getSinger());
        Assert.assertEquals(2000, this.tm.getAlbum(idAlbum).getYear());

        album.setId(idAlbum);
        album.setName("Name2");
        album.setSinger("Singer2");
        album.setYear(2002);
        this.tm.updateAlbum(album);

        Assert.assertEquals("Name2", this.tm.getAlbum(idAlbum).getName());
        Assert.assertEquals("Singer2", this.tm.getAlbum(idAlbum).getSinger());
        Assert.assertEquals(2002, this.tm.getAlbum(idAlbum).getYear());
    }

    @Test(expected = AlbumNotFoundException.class)
    public void testUpdateAlbumAndAlbumNotFound() throws AlbumNotFoundException {
        this.tm.updateAlbum(new Album("Name", "Singer", 2000));
    }

    @Test
    public void testDeleteTrack() throws AlbumNotFoundException, TrackNotFoundException {
        String idAlbum = this.tm.addAlbum("Album", "Singer", 2000);
        String idTrack = this.tm.addTrack("Title", "Singer", idAlbum);

        Assert.assertEquals(5, this.tm.numTracks());

        this.tm.deleteTrack(idTrack);

        Assert.assertEquals(4, this.tm.numTracks());
    }

    @Test(expected = TrackNotFoundException.class)
    public void testDeleteTrackAndTrackNotFound() throws TrackNotFoundException {
        this.tm.deleteTrack(null);
    }

    @Test
    public void testDeleteAlbum() throws AlbumNotFoundException {
        String idAlbum = this.tm.addAlbum("Album", "Singer", 2000);

        Assert.assertEquals(4, this.tm.numAlbums());

        this.tm.deleteAlbum(idAlbum);

        Assert.assertEquals(3, this.tm.numAlbums());
    }

    @Test(expected = AlbumNotFoundException.class)
    public void testDeleteAlbumAndAlbumNotFound() throws AlbumNotFoundException {
        this.tm.deleteAlbum(null);
    }
}
