package com.example.demo.persistence;

import com.example.demo.models.hibernate.Films;

import java.util.List;
import java.util.Optional;

public interface IFilmsRepo {
    Optional<Films> getFilms(int id);
    List<Films> searchFilms(
        String title,
        int yearFrom,
        int yearTo,
        List<String> countries,
        List<String> genres,
        List<String> languages,
        List<String> actors,
        List<String> directors
    );
}
