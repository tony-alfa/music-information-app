package com.expd.dao;

import com.expd.model.Artist;
import com.expd.model.Genre;
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
public class ArtistDAOTest {

    private Artist artist1;
    private Artist artist2;

    private String newName = "Different Name";

    @Autowired
    private BaseDAO<Artist> artistDAO;

    @BeforeEach
    public void setup() {
        artist1 = new Artist("Antony Alfaro", "SJO", Genre.METAL);
        artist2 = new Artist();

        artistDAO = new ArtistDAO();
        artistDAO.deleteStore();
        artistDAO.createStore();
    }

    @Test
    public void testCreate(){
        int newId = artistDAO.create(artist1).getId();

        Artist resultArtist = artistDAO.get(newId);

        assertEquals(newId, resultArtist.getId());
    }

    @Test
    public void testUpdate() {
        artist1 = artistDAO.create(artist1);
        int newId = artist1.getId();

        Artist resultArtist = artistDAO.get(newId);

        assertEquals(newId, resultArtist.getId());

        artist1.setName(newName);
        artistDAO.update(artist1);

        resultArtist = artistDAO.get(artist1.getId());
        assertEquals(newName, resultArtist.getName());
    }

    @Test
    public void testGetAll() {
        List<Artist> artists = artistDAO.getAll();
        int oldCount = artists.size();

        artistDAO.create(artist1);

        artists = artistDAO.getAll();
        assertEquals(oldCount + 1, artists.size());
    }

    @Test
    public void testGetTrackWithExistingId() {
        artistDAO.create(artist1);
        List<Artist> artists = artistDAO.getAll();
        Artist artist = artistDAO.get(artists.getFirst().getId());
        assertNotNull(artist);
    }

    @Test
    public void testGetTrackWithNonExistingId() {
        Artist artist = artistDAO.get(1000);
        assertNull(artist);
    }

}
