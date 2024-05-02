package com.expd.service;

import com.expd.dao.TrackDAO;
import com.expd.model.MediaType;
import com.expd.model.Track;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class TrackServiceTest {
    @Mock
    private TrackDAO trackDAO;
    @InjectMocks
    private TrackService trackService;

    @Test
    public void testCreateTrack(){
        Track track = new Track("Californication","Deluxe Edition", LocalDate.of(2000,12,1),3, MediaType.MP3);

        Mockito.when(trackDAO.create(track)).thenReturn(track);

        Track track1 = trackService.create(track);

        Mockito.verify(trackDAO).create(track);
    }

    @Test
    public void testDeleteStudent(){
        Track track = trackService.create(new Track("Californication","Deluxe Edition", LocalDate.of(2000,12,1),3, MediaType.MP3));
        track.setId(1);

        Mockito.when(trackDAO.delete(track)).thenReturn(true);

        boolean result = trackService.delete(track.getId());
        assertTrue(result);

        Mockito.verify(trackDAO).delete(track);
    }
}


