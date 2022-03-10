package com.example.demo.models.views;

import com.example.demo.models.hibernate.Films;

public class MovieListItemView {
    public final int id;
    public final String title;
    public final int year;

    public MovieListItemView(int id, String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public static MovieListItemView fromFilms(Films films) {
        return new MovieListItemView(
                films.getFilmid().intValue(),
                films.getTitre(),
                films.getAnneedesortie().intValue()
        );
    }
}
