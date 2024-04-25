package com.expd.service;

import com.expd.dao.BaseDAO;
import com.expd.model.MediaType;
import com.expd.dao.TrackDAO;
import com.expd.model.Track;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TrackService {
    private BaseDAO<Track> trackDAO;

    public TrackService(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public Track create(Track track){
        return trackDAO.create(track);
    }

    public boolean update(Track track){
        return trackDAO.update(track);
    }

    public boolean delete(int id){
        Track track = getById(id);
        // Validate if exist

        return trackDAO.delete(track);
    }

    public List<Track> getAll(){
        return trackDAO.getAll();
    }

    public Track getById(int id){
        return trackDAO.get(id);
    }

    public List<Track> findByExample(Map<String, Object> example){
        Track track = new Track();
        for(var entry : example.entrySet()) {
            switch(entry.getKey()) {
                case "title" -> track.setTitle((String) entry.getValue());
                case "mediaType" -> track.setMediaType(MediaType.valueOf(entry.getValue().toString()));
                case "duration" -> {
                    track.setDuration(Integer.parseInt(entry.getValue().toString()));
                }
            };
        }

        return trackDAO.findByExample(track);
    }
}
