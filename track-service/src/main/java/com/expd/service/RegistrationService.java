package com.expd.service;

import com.expd.model.Artist;
import com.expd.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private TrackService trackService;
    private ArtistService artistService;

    public RegistrationService(TrackService trackService, ArtistService artistService) {
        this.trackService = trackService;
        this.artistService = artistService;
    }

    public void registerArtistToTrack(int artistId, int trackId) {
        Artist artist = artistService.getById(artistId);
        Track track = trackService.getById(trackId);

        track.getArtists().add(artist);
    }

    public List<Track> getTracksForArtist(int artistId) {
        List<Track> tracks = trackService.getAll();
        Artist artist = artistService.getById(artistId);
        return tracks.stream().filter(track -> track.getArtists().contains(artist)).toList();
    }

}
