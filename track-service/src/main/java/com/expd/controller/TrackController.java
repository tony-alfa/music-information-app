package com.expd.controller;

import com.expd.dto.TrackResponseDTO;
import com.expd.model.Track;
import com.expd.pricing.PricingProvider;
import com.expd.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/track")
public class TrackController {
    private TrackService trackService;
    private PricingProvider pricingProvider;

    public TrackController(TrackService trackService, PricingProvider pricingProvider) {
        this.trackService = trackService;
        this.pricingProvider = pricingProvider;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(
                trackService.getAll()
                        .stream()
                        .map(track -> TrackResponseDTO.fromTrack(track, pricingProvider.getPricing(track.getId()))).toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        Track track = trackService.getById(id);
        if(track == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Track with id: " + id);
        }
        return ResponseEntity.ok(TrackResponseDTO.fromTrack(track, pricingProvider.getPricing(id)));
    }
    @GetMapping("/findBy")
    public ResponseEntity<?> findByExample(@RequestParam Map<String, Object> params){
        List<Track> tracks = trackService.findByExample(params);
        if(tracks.isEmpty()){
            StringBuilder str = new StringBuilder();
            for(var entry : params.entrySet()) {
                 str.append(entry);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Tracks with: " + str);
        }
        return ResponseEntity.ok(tracks.stream().map(track -> TrackResponseDTO.fromTrack(track, pricingProvider.getPricing(track.getId()))).toList());
    }
    @PostMapping
    public Track create(@RequestBody Track track){
        return trackService.create(track);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Track track){
        boolean result = trackService.update(track);
        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Track with id: " + track.getId());
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        boolean result = trackService.delete(id);
        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Track with id: " + id);
        }
        return ResponseEntity.noContent().build();
    }
}
