package tr.com.huseyinaydin.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tr.com.huseyinaydin.entity.Student;
import tr.com.huseyinaydin.util.HibernateUtil;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Hibernate.
* 
*/

public class StudentDao {
	public void saveStudent(Student student) {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(student);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Student> getStudents() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Student", Student.class).list();
		}
	}
}