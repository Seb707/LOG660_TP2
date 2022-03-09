

import org.hibernate.Session;

public class hibernateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
	}

}
