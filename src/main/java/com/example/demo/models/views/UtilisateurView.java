package com.example.demo.models.views;

import com.example.demo.models.hibernate.Utilisateurs;

public class UtilisateurView {
    public final String courriel;

    public UtilisateurView(String courriel) {
        this.courriel = courriel;
    }

    public static UtilisateurView fromUtilisateur(Utilisateurs utilisateur) {
        return new UtilisateurView(utilisateur.getCourriel());
    }
}
