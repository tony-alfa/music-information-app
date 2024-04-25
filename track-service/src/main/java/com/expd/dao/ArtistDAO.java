package com.expd.dao;

import com.expd.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ArtistDAO implements BaseDAO<Artist>{
    private Map<Integer, Artist> artists = new ConcurrentHashMap<>();
    private static final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public boolean update(Artist artist) {
        return artists.replace(artist.getId(), artist) != null;
    }

    @Override
    public boolean delete(Artist artist) {
        return artists.remove(artist.getId()) != null;
    }

    @Override
    public Artist create(Artist artist) {
        int newId = nextId.getAndIncrement();
        artist.setId(newId);
        artists.put(newId, artist);

        return artist;
    }

    @Override
    public Artist get(int id) {
        return artists.get(id);
    }

    @Override
    public List<Artist> getAll() {
        return artists.values().stream().toList();
    }

    @Override
    public void deleteStore() {
        artists = null;
    }

    @Override
    public void createStore() {
        artists = new ConcurrentHashMap<>();
    }
}
