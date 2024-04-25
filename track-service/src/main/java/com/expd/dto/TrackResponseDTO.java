package com.expd.dto;

import com.expd.model.Genre;
import com.expd.model.MediaType;
import com.expd.model.Track;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class TrackResponseDTO {
    private int id;
    private String title;
    private String album;
    private List<ArtistResponseForTrack> artists;
    private LocalDate issueDate;
    private int duration;
    private MediaType mediaType;
    private int price;

    @Builder
    @Getter
    @Setter
    public static class ArtistResponseForTrack {
        private int id;
        private String name;
        private String nationality;
        private Genre genre;
    }

    public static TrackResponseDTO fromTrack(Track track, int price) {
        var artistList = track.getArtists()
                .stream()
                .map(artist -> ArtistResponseForTrack
                        .builder()
                        .id(artist.getId())
                        .name(artist.getName())
                        .nationality(artist.getNationality())
                        .genre(artist.getGenre())
                        .build())
                .toList();

        return TrackResponseDTO.builder()
                .id(track.getId())
                .title(track.getTitle())
                .album(track.getAlbum())
                .artists(artistList)
                .issueDate(track.getIssueDate())
                .duration(track.getDuration())
                .mediaType(track.getMediaType())
                .price(price)
                .build();
    }
}
