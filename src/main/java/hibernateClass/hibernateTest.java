package hibernateClass;

import java.util.Iterator;

import org.hibernate.Session;

import hibernate.Clients;

public class hibernateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		
		Session session = HibernateUtil.getSessionFactory().openSession();

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
		

	}
}
