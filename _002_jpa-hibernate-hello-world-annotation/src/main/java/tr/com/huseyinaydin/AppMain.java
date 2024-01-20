package tr.com.huseyinaydin;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tr.com.huseyinaydin.model.Customer;
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

        Customer customer = new Customer();
        customer.setFirstname("Hüseyin");
        customer.setLastname("Aydın");
        customer.setAddress("localhost:9999");
        customer.setCreationDate(new Date());

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

            System.err.println("Müşteri nesnesi kaydolurken hata oldu aha dayıya sor : " + e);
            e.printStackTrace();
            
        } finally {
            session.close();
        }
    }
}