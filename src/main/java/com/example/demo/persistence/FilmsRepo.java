package com.example.demo.persistence;

import com.example.demo.models.hibernate.Films;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class FilmsRepo implements IFilmsRepo {
    private SessionFactory sessionFactory;

    public FilmsRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Films> getFilms(int id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public List<Films> searchFilms(String title, int yearFrom, int yearTo, List<String> countries, List<String> genres, List<String> languages, List<String> actors, List<String> directors) {
        // TODO
        return List.of();
    }
}
