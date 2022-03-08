package com.example.demo.persistence;

import com.example.demo.models.Utilisateur;

import java.util.Optional;

public interface IUtilisateursRepo {
    Optional<Utilisateur> getUtilisateur(String courriel);
    Optional<Utilisateur> validateUtilisateurCredentials(String courriel, String motDePasse);
}
