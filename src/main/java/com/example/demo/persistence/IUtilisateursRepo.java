package com.example.demo.persistence;

import com.example.demo.models.hibernate.Utilisateurs;

import java.util.Optional;

public interface IUtilisateursRepo {
    Optional<Utilisateurs> getUtilisateur(String courriel);
    Optional<Utilisateurs> validateUtilisateurCredentials(String courriel, String motDePasse);
}
