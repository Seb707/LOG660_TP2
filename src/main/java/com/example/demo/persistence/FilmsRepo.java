package com.example.demo.persistence;

import com.example.demo.models.hibernate.Films;
import com.example.demo.models.hibernate.Utilisateurs;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
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
    public List<Films> searchFilms(String title, int yearFrom, int yearTo, List<String> countries, List<String> genres, String language, List<String> actors, String director) {
        Session session = null;
        List films = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String query =  "FROM Films f ";
            List<String> condition = new ArrayList<>();

            if(countries != null && !countries.isEmpty()) {
                // query += "INNER JOIN Paysdeproductionfilm ppf ON ppf.filmId = f.filmId ";
                query += "INNER JOIN Paysdeproduction pp ";
                String coutryCondition = "";
                for(String c : countries) {
                    coutryCondition += "pp.pays = '" + c + "' OR ";
                }
                coutryCondition = coutryCondition.substring(0, coutryCondition.length() - 4);
                condition.add(coutryCondition);
            }

            if(director != null && !director.isEmpty()) {
                query += "INNER JOIN Personnes p ON p.personneId = f.realisateurId ";
                condition.add("p.nom LIKE '" + director + "'");
            }

            if(actors != null && !actors.isEmpty()) {
                query += "INNER JOIN Roleacteurs ra ON ra.filmId = f.filmId ";
                query += "INNER JOIN Personnes p2 ON ra.personneId = p2.personneId ";
                String actorCondition = "";
                for(String a : actors) {
                    actorCondition += "p2.nom LIKE '%" + a + "%' OR ";
                }
                actorCondition = actorCondition.substring(0, actorCondition.length() - 4);
                condition.add(actorCondition);
            }

            if(title != null && !title.isEmpty()) {
                condition.add("f.titre LIKE '" + title + "'");
            }

            if(condition.size() > 0) {
                String conditionString = "";
                for(String c : condition) {
                    conditionString += "(" + c + ") AND ";
                }
                conditionString = conditionString.substring(0, conditionString.length() - 5);
                query += "WHERE " + conditionString;
            }
            System.out.println("tests 3");
            System.out.println(query);


            films = session.createQuery(query).setMaxResults(10).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error get movies: " + e.getMessage());
        } finally {
          if(session != null)
              session.close();
        }
        return films;
    }

    class Param {
        public String name;
        public String value;
        public Param(String name, String value){
            this.name = name;
            this.value = value;
        }
    }
}
