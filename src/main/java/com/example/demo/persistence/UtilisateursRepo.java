package com.example.demo.persistence;

import com.example.demo.models.hibernate.Utilisateurs;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UtilisateursRepo implements IUtilisateursRepo {
    private final SessionFactory sessionFactory;

    @Autowired
    public UtilisateursRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Utilisateurs> getUtilisateur(String courriel) {
        var session = sessionFactory.openSession();
        session.beginTransaction();
        var query = session.createQuery("FROM Utilisateurs WHERE courriel=:courriel");
        query.setParameter("courriel", courriel);
        var response = (List<Utilisateurs>)query.list();
        session.getTransaction().commit();
        session.close();
        if (response.isEmpty())
            return Optional.empty();
        else
            return Optional.of(response.get(0));
    }

    @Override
    public Optional<Utilisateurs> validateUtilisateurCredentials(String courriel, String motDePasse) {
        var session = sessionFactory.openSession();
        session.beginTransaction();
        var query = session.createQuery("FROM Utilisateurs WHERE courriel=:courriel AND motdepasse=:motDePasse");
        query.setParameter("courriel", courriel);
        query.setParameter("motDePasse", motDePasse);
        var response = (List<Utilisateurs>)query.list();
        session.getTransaction().commit();
        session.close();
        if (response.isEmpty())
            return Optional.empty();
        else
            return Optional.of(response.get(0));
    }
}
