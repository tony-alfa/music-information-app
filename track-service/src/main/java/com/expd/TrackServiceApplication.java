package com.expd;

import com.expd.model.Genre;
import com.expd.model.MediaType;
import com.expd.model.Artist;
import com.expd.model.Track;
import com.expd.service.ArtistService;
import com.expd.service.RegistrationService;
import com.expd.service.TrackService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class TrackServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackServiceApplication.class, args);
	}

}

@Component
class MyRunner implements CommandLineRunner
{
	private ArtistService artistService;
	private TrackService trackService;
	private RegistrationService registrationService;

	public MyRunner(ArtistService artistService, TrackService trackService, RegistrationService registrationService) {
		this.artistService = artistService;
		this.trackService = trackService;
		this.registrationService = registrationService;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("MyRunner called");

		var tracks = List.of(
				new Track("Lonely Day","Hypnotize", Set.of(
						artistService.create(new Artist("Antony Alfaro", "SJO", Genre.METAL)),
						artistService.create(new Artist("Pepe Quiros", "PTY", Genre.POP))
				), LocalDate.of(2000,12,1),3, MediaType.MP3),
				new Track("Californication","Deluxe Edition", LocalDate.of(2000,12,1),3, MediaType.MP3),
				new Track("Attack","Hypnotize", LocalDate.of(2000,12,1),1, MediaType.MP3)
		);

		tracks.forEach(trackService::create);

		List<Track> trackList = trackService.getAll();
		System.out.println("tracks: " + trackList.size());
		System.out.println(trackList);

		registrationService.registerArtistToTrack(1,3);
		registrationService.registerArtistToTrack(2,2);

		System.out.println(trackList);

		System.out.println(registrationService.getTracksForArtist(2));
	}
}
