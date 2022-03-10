package hibernateUtilities;

import java.util.Iterator;

import org.hibernate.Session;

import hibernate.Clients;

public class hibernateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			session.beginTransaction();
			
			Iterator<Clients> iter1 = session.createQuery("FROM Clients").list().iterator(); 
			
			while (iter1.hasNext()){
				Clients client = (Clients) iter1.next(); 
				System.out.println("Nom: "+client.getUtilisateurs().getNom()); 
				
			}
			session.getTransaction().commit();
			session.close();
			HibernateUtil.shutdown();
			System.out.println("Session terminee");
			
		}finally {
			System.out.println(":)");

		}

		
//		  <mapping class="hibernate/Bandeannonces"/>
//		  <mapping class="hibernate/BandeannoncesId"/>
//		  <mapping class="hibernate/Clients"/>
//		  <mapping class="hibernate/Copiefilms"/>
//		  <mapping class="hibernate/CopiefilmsdispoId"/>
//		  <mapping class="hibernate/Employes"/>
//		  <mapping class="hibernate/Films"/>
//		  <mapping class="hibernate/Forfaits"/>
//		  <mapping class="hibernate/Genres"/>
//		  <mapping class="hibernate/Locations"/>
//		  <mapping class="hibernate/LocationsID"/>
//		  <mapping class="hibernate/Paysdeproduction"/>
//		  <mapping class="hibernate/Personnes"/>
//		  <mapping class="hibernate/Roleacteurs"/>
//		  <mapping class="hibernate/RoleacteursId"/>
//		  <mapping class="hibernate/Scenaristes"/>
//		  <mapping class="hibernate/Utilisateurs"/>
//		  <mapping resource="hibernate/Bandeannonces.hbm.xml"/>
//		  <mapping resource="hibernate/Clients.hbm.xml"/>
//		  <mapping resource="hibernate/Copiefilms.hbm.xml"/>
//		  <mapping resource="hibernate/Copiefilmsdispo.hbm.xml"/>
//		  <mapping resource="hibernate/Employes.hbm.xml"/>
//		  <mapping resource="hibernate/Forfaits.hbm.xml"/>
//		  <mapping resource="hibernate/Films.hbm.xml"/>
//		  <mapping resource="hibernate/Genres.hbm.xml"/>
//		  <mapping resource="hibernate/Locations.hbm.xml"/>
//		  <mapping resource="hibernate/Paysdeproduction.hbm.xml"/>
//		  <mapping resource="hibernate/Personnes.hbm.xml"/>
//		  <mapping resource="hibernate/Roleacteurs.hbm.xml"/>
//		  <mapping resource="hibernate/Scenaristes.hbm.xml"/>
//		  <mapping resource="hibernate/Utilisateurs.hbm.xml"/>
	}
}
