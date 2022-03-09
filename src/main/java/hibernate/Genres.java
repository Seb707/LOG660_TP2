package hibernate;

// default package
// Generated Mar. 9, 2022, 1:40:14 p.m. by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Genres generated by hbm2java
 */
public class Genres implements java.io.Serializable {

	private String genre;
	private Set filmses = new HashSet(0);

	public Genres() {
	}

	public Genres(String genre) {
		this.genre = genre;
	}

	public Genres(String genre, Set filmses) {
		this.genre = genre;
		this.filmses = filmses;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Set getFilmses() {
		return this.filmses;
	}

	public void setFilmses(Set filmses) {
		this.filmses = filmses;
	}

}