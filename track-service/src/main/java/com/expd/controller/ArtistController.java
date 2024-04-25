package com.expd.controller;

import com.expd.model.Artist;
import com.expd.service.ArtistService;
import com.expd.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.OpenURIEvent;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {
    private ArtistService artistService;
    private UriCreator uriCreator;
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(artistService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        Artist artist = artistService.getById(id);
        if (artist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Artist with id: " + id);
        }
        return ResponseEntity.ok(artist);
    }

    @GetMapping ("/byname/{name}")
    public ResponseEntity<?> getArtistByName(@PathVariable String name) {
        List<Artist> result = artistService.getByName(name);
        if(result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Artist with name: " + name);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Artist artist){
        Artist newArtist = artistService.create(artist);

        URI uri = uriCreator.getURI(newArtist.getId());

        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Artist artist){
        boolean result = artistService.update(artist);
        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Artist with id: " + artist.getId());
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        boolean result = artistService.delete(id);
        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Artist with id: " + id);
        }
        return ResponseEntity.noContent().build();
    }
}
