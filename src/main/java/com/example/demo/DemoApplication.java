
package com.example.demo;
import com.example.demo.configuration.JwtTokenUtil;
import com.example.demo.models.hibernate.Utilisateurs;
import com.example.demo.models.input.LoginInput;
import com.example.demo.models.views.MovieListItemView;
import com.example.demo.models.views.MovieView;
import com.example.demo.models.views.PersonView;
import com.example.demo.models.views.UtilisateurView;
import com.example.demo.persistence.FilmsRepo;
import com.example.demo.persistence.IFilmsRepo;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class DemoApplication {
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;
	private final IFilmsRepo filmsRepo;

	public DemoApplication(
			AuthenticationManager authenticationManager,
			JwtTokenUtil jwtTokenUtil,
			EntityManagerFactory entityManagerFactory
	) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.filmsRepo = new FilmsRepo(entityManagerFactory.unwrap(SessionFactory.class));
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<UtilisateurView> login(@RequestBody LoginInput input) {
		try {
			var authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(input.email, input.password)
			);

			var user = (Utilisateurs) authentication.getPrincipal();
			return ResponseEntity.ok()
					.header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
					.body(UtilisateurView.fromUtilisateur(user));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@GetMapping("/movies")
	public ResponseEntity<List<MovieListItemView>> getMoviesList(
			@RequestParam(required = false) String title,
			@RequestParam(required = false, defaultValue = "-1") int yearFrom,
			@RequestParam(required = false, defaultValue = "-1") int yearTo,
			@RequestParam(required = false) List<String> countries,
			@RequestParam(required = false) List<String> genres,
			@RequestParam(required = false) String language,
			@RequestParam(required = false) List<String> actors,
			@RequestParam(required = false) String director
	) {
		var x =
				filmsRepo.searchFilms(
					 title,
					 yearFrom,
					 yearTo,
					 countries,
					 genres,
					 language,
					 actors,
					 director
				)
				.stream()
				.map(MovieListItemView::fromFilms);
				var y = x.collect(Collectors.toList());
				return ResponseEntity.ok(y);
	}

	@GetMapping("/movies/{id}")
	public ResponseEntity<MovieView> getMovie(@PathVariable int id) {
		// TODO
		return ResponseEntity.ok(null);
	}

	@PostMapping("/movies/{id}")
	public ResponseEntity<MovieView> rentMovie(@PathVariable int id) {
		// TODO
		return ResponseEntity.ok(null);
	}

	@GetMapping("/persons/{id}")
	public ResponseEntity<PersonView> getPerson(@PathVariable int id) {
		// TODO
		return ResponseEntity.ok(null);
	}
}
            