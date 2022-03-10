package hibernateUtilities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Session;

import hibernate.Clients;
import hibernate.Copiefilms;
import hibernate.Locations;
import hibernate.LocationsId;
import java.time.LocalDateTime;
import java.time.ZoneId;  

public class bdRequest {

	
	public boolean ClientTrylogin(String courrielLogin, String passwordLogin) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		Iterator<Clients> iter1 = session.createQuery("FROM Clients").list().iterator(); 
		
		while (iter1.hasNext()){
			Clients client = (Clients) iter1.next(); 
			
			if(client.getCourriel() == courrielLogin) {
				if(client.getUtilisateurs().getMotdepasse() == passwordLogin) {
					session.getTransaction().commit();
					session.close();
					HibernateUtil.shutdown();
					return true;
				}else {
					session.getTransaction().commit();
					session.close();
					HibernateUtil.shutdown();
					return false;}
			}
		}
		session.getTransaction().commit();
		session.close();
		HibernateUtil.shutdown();
		return false;
	}
	
	public String ClientTryLocationFilm(String courrielClient, int filmId) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		Iterator<Clients> iter1 = session.createQuery("FROM Clients").list().iterator(); 
		
		Iterator<Copiefilms> iter2 = session.createQuery("FROM Copiefilms").list().iterator(); 
		
		while (iter2.hasNext()){
			Copiefilms copieFilms = (Copiefilms) iter2.next(); 
			
			if(copieFilms.getFilms().getFilmid().intValue() == filmId) {
				if(copieFilms.getNocopie().intValue() > 0) {
					while (iter1.hasNext()){
						Clients client = (Clients) iter1.next(); 
						if(client.getCourriel() == courrielClient) {
							Iterator<Locations> iter3 = client.getLocationses().iterator();
							int locationCount = 0;
							while(iter3.hasNext()) {
								Locations location = (Locations) iter3.next(); 
								locationCount +=1;
							}
							if(client.getForfaits().getLocationmax().intValue() <= locationCount) {
								copieFilms.setNocopie(copieFilms.getNocopie().subtract(BigDecimal.valueOf(1)));
								LocationsId newId = new LocationsId(courrielClient, copieFilms.getNocopie());
								Date in = new Date();
								LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
								Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
								Locations location = new Locations(newId, client, copieFilms, out);
								session.getTransaction().commit();
								session.close();
								HibernateUtil.shutdown();
								return "location du film "+copieFilms.getFilms().getTitre()+" à "+client.getCourriel()+" réussi.";
							}
							session.getTransaction().commit();
							session.close();
							HibernateUtil.shutdown();
							return "Nombre de location atteint pour client "+client.getCourriel();
						}
					}
				}else {
					session.getTransaction().commit();
					session.close();
					HibernateUtil.shutdown();
					return "Il ne reste plus de copie pour le film "+copieFilms.getFilms().getTitre();}
			}
		}
		session.getTransaction().commit();
		session.close();
		HibernateUtil.shutdown();
		return "Acune copie du film a ete trouve";

	}
}
