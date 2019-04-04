package edu.upc.dsa.services;


import edu.upc.dsa.AlbumNotFoundException;
import edu.upc.dsa.TrackNotFoundException;
import edu.upc.dsa.TracksManager;
import edu.upc.dsa.TracksManagerImpl;
import edu.upc.dsa.models.Album;
import edu.upc.dsa.models.Track;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/tracks", description = "Endpoint to Track Service")
@Path("/tracks")
public class TracksService {

    private TracksManager tm;

    public TracksService() throws AlbumNotFoundException {

        this.tm = TracksManagerImpl.getInstance();
        if (this.tm.numAlbums() == 0 && this.tm.numTracks() == 0) {

            String idAlbum1 = this.tm.addAlbum("Para Todos Los Publicos", "Georgie Dann", 2001);
            this.tm.addTrack("La Barbacoa", "Georgie Dann", idAlbum1);
            this.tm.addTrack("Carnaval, Carnaval", "Georgie Dann", idAlbum1);

            String idAlbum2 = this.tm.addAlbum("Vida", "Luis Fonsi", 2019);
            this.tm.addTrack("Despacito", "Luis Fonsi", idAlbum2);

            String idAlbum3 = this.tm.addAlbum("Metallica", "Metallica", 1991);
            this.tm.addTrack("Enter Sandman", "Metallica", idAlbum3);
        }

    }

    @GET
    @ApiOperation(value = "get all tracks")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Track.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks() {
        List<Track> tracks = this.tm.getTracks();
        GenericEntity<List<Track>> entity = new GenericEntity<List<Track>>(tracks) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get all albums")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Album.class, responseContainer="List"),
    })
    @Path("/albums")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlbums() {
        List<Album> albums = this.tm.getAlbums();
        GenericEntity<List<Album>> entity = new GenericEntity<List<Album>>(albums) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get a Track")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Track.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("id") String id) {
        try {
            Track track = this.tm.getTrack(id);
            GenericEntity<Track> entity = new GenericEntity<Track>(track) {};
            return Response.status(201).entity(entity).build();
        } catch (TrackNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "get an Album")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Album.class),
            @ApiResponse(code = 404, message = "Album not found")
    })
    @Path("/albums/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlbum(@PathParam("id") String id) {
        try {
            Album album = this.tm.getAlbum(id);
            GenericEntity<Album> entity = new GenericEntity<Album>(album) {};
            return Response.status(201).entity(entity).build();
        } catch (AlbumNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @DELETE
    @ApiOperation(value = "delete a Track")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    public Response deleteTrack(@PathParam("id") String id) {
        try {
            this.tm.deleteTrack(id);
            return Response.status(201).build();
        } catch (TrackNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @DELETE
    @ApiOperation(value = "delete an Album")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Album not found")
    })
    @Path("/albums/{id}")
    public Response deleteAlbum(@PathParam("id") String id) {
        try {
            this.tm.deleteAlbum(id);
            return Response.status(201).build();
        } catch (AlbumNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @PUT
    @ApiOperation(value = "update a Track")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateTrack(Track track) {
        try {
            this.tm.updateTrack(track);
            return Response.status(201).build();
        } catch (TrackNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @PUT
    @ApiOperation(value = "update an Album")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Album not found")
    })
    @Path("/")
    public Response updateTrack(Album album) {
        try {
            this.tm.updateAlbum(album);
            return Response.status(201).build();
        } catch (AlbumNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @POST
    @ApiOperation(value = "create a new Track")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Album not found"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/{idAlbum}")
    public Response newTrack(Track track, @PathParam("idAlbum") String idAlbum) {

        if (track.getTitle() == null || track.getSinger() == null || idAlbum == null) return Response.status(500).build();
        try {
            this.tm.addTrack(track.getTitle(), track.getSinger(), idAlbum);
            return Response.status(200).build();
        } catch (AlbumNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

}