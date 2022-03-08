package com.example.demo.models.views;

import com.example.demo.models.Utilisateur;

public class UtilisateurView {
    public final String courriel;

    public UtilisateurView(String courriel) {
        this.courriel = courriel;
    }

    public static UtilisateurView fromUtilisateur(Utilisateur utilisateur) {
        return new UtilisateurView(utilisateur.courriel);
    }
}
