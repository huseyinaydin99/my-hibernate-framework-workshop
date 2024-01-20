package tr.com.huseyinaydin;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tr.com.huseyinaydin.model.Customer;
import tr.com.huseyinaydin.model.CustomerDetail;
import tr.com.huseyinaydin.util.HibernateUtil;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Hibernate.
* 
*/

public class AppMain {

	public static void main(String[] args) {

		Customer customer = new Customer("Reis", "Hüseyin AYDIN");
		CustomerDetail customerDetail = new CustomerDetail("lcoalhost:9999", "0500 000 00 00", new Date());

		customer.setCustomerDetail(customerDetail);
		customerDetail.setCustomer(customer);

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(customer);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println("Müşteri oluşturulurken hata oluştu aha dayıya sorver dayı : " + e);
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}