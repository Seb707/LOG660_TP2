package com.example.demo.models.views;

import java.util.List;

public class MovieView {
    public MovieView(int id, String title, String shortDescription, String posterUrl, int year, List<String> countries, String language, int duration, List<String> genres, int directorId, String director, List<String> screenwriters, List<MovieActorView> actors) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.posterUrl = posterUrl;
        this.year = year;
        this.countries = countries;
        this.language = language;
        this.duration = duration;
        this.genres = genres;
        this.directorId = directorId;
        this.director = director;
        this.screenwriters = screenwriters;
        this.actors = actors;
    }

    public static class MovieActorView {
        public final int id;
        public final String name;
        public final String role;

        public MovieActorView(int id, String name, String role) {
            this.id = id;
            this.name = name;
            this.role = role;
        }
    }

    public final int id;
    public final String title;
    public final String shortDescription;
    public final String posterUrl;
    public final int year;
    public final List<String> countries;
    public final String language;
    public final int duration;
    public final List<String> genres;
    public final int directorId;
    public final String director;
    public final List<String> screenwriters;
    public final List<MovieActorView> actors;
}
