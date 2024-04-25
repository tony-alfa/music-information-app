package com.expd.service;

import com.expd.dao.ArtistDAO;
import com.expd.dao.BaseDAO;
import com.expd.model.Artist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    private BaseDAO<Artist> artistDAO;

    public ArtistService(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    public Artist create(Artist artist){
        return artistDAO.create(artist);
    }

    public boolean update(Artist artist){
        return artistDAO.update(artist);
    }

    public boolean delete(int id){
        Artist artist = getById(id);
        // Validate if exist

        return artistDAO.delete(artist);
    }

    public List<Artist> getAll(){
        return artistDAO.getAll();
    }

    public Artist getById(int id) {
        return artistDAO.get(id);
    }

    public List<Artist> getByName(String name) {
        List<Artist> result = artistDAO.findBy(s -> s.getName().contains(name));

        return result;
    }
}
