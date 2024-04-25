package com.expd.dao;

import com.expd.model.MediaType;
import com.expd.model.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrackDAOTest {

    private Track track1;
    private Track track2;

    private String newTitle = "Different Title";

    @Autowired
    private BaseDAO<Track> trackDAO;

    @BeforeEach
    public void setup() {
        track1 = new Track("Californication","Deluxe Edition", LocalDate.of(2000,12,1),3, MediaType.MP3);
        track2 = new Track();

        trackDAO = new TrackDAO();
        trackDAO.deleteStore();
        trackDAO.createStore();
    }

    @Test
    public void testCreate(){
        int newId = trackDAO.create(track1).getId();

        Track resultTrack = trackDAO.get(newId);

        assertEquals(newId, resultTrack.getId());
    }

    @Test
    public void testUpdate() {
        track1 = trackDAO.create(track1);
        int newId = track1.getId();

        Track resultTrack = trackDAO.get(newId);

        assertEquals(newId, resultTrack.getId());

        track1.setTitle(newTitle);
        trackDAO.update(track1);

        resultTrack = trackDAO.get(track1.getId());
        assertEquals(newTitle, resultTrack.getTitle());
    }

    @Test
    public void testGetAll() {
        List<Track> tracks = trackDAO.getAll();
        int oldCount = tracks.size();

        trackDAO.create(track1);

        tracks = trackDAO.getAll();
        assertEquals(oldCount + 1, tracks.size());
    }

    @Test
    public void testGetTrackWithExistingId() {
        trackDAO.create(track1);
        List<Track> tracks = trackDAO.getAll();
        Track track = trackDAO.get(tracks.getFirst().getId());
        assertNotNull(track);
    }

    @Test
    public void testGetTrackWithNonExistingId() {
        Track track = trackDAO.get(1000);
        assertNull(track);
    }

}
