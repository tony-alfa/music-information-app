package com.expd.dao;

import com.expd.model.Track;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

@Repository
public class TrackDAO implements BaseDAO<Track> {

    private Map<Integer, Track> tracks = new ConcurrentHashMap<>();
    private static final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public boolean update(Track track) {
        return tracks.replace(track.getId(), track) != null;
    }

    @Override
    public boolean delete(Track track) {
        return tracks.remove(track.getId()) != null;
    }

    @Override
    public Track create(Track track) {
        int newId = nextId.getAndIncrement();
        track.setId(newId);
        tracks.put(newId, track);

        return track;
    }

    @Override
    public Track get(int id) {
        return tracks.get(id);
    }

    @Override
    public List<Track> getAll() {
        return tracks.values().stream().toList();
    }

    public List<Track> findByExample(Track example) {

        Predicate<Track> predicate = null;
        if (example.getTitle() != null) {
            predicate = c -> c.getTitle().contains(example.getTitle());
        }
        if (example.getMediaType() != null) {
            Predicate<Track> pr = c -> c.getMediaType().equals(example.getMediaType());
            predicate = predicate == null ? pr : predicate.or(pr);
        }

        predicate = predicate == null ? (t ->true) : predicate;


        return getAll()
                .stream()
                .filter(predicate)
                .toList();
    }

    @Override
    public void deleteStore() {
        tracks = null;
    }

    @Override
    public void createStore() {
        tracks = new ConcurrentHashMap<>();
    }
}
