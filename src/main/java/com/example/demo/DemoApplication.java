
package com.example.demo;
import com.example.demo.configuration.JwtTokenUtil;
import com.example.demo.models.input.LoginInput;
import com.example.demo.models.Utilisateur;
import com.example.demo.models.views.MovieListItemView;
import com.example.demo.models.views.UtilisateurView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;

	public DemoApplication(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@PostMapping("/login")
	public ResponseEntity<UtilisateurView> login(@RequestBody LoginInput input) {
		try {
			var authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(input.email, input.password)
			);

			var user = (Utilisateur) authentication.getPrincipal();
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
			@RequestParam(required = false) int yearFrom,
			@RequestParam(required = false) int yearTo,
			@RequestParam(required = false) String country,
			@RequestParam(required = false) String language,
			@RequestParam(required = false) String actor
	) {
		return ResponseEntity.ok(List.of());
	}
}
            