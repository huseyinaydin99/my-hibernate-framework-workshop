package tr.com.huseyinaydin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import tr.com.huseyinaydin.model.Address;
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
      Session session = HibernateUtil.getSessionFactory().openSession();

      try {
          session.beginTransaction();
          // Adres nesneleri oluşturuluyor.
          Address addr1 = new Address("A Sokak", "A Sehri", "A Ülkesi", "Tip 1");
          Address addr2 = new Address("B Sokak", "B Sehri", "B Ülkesi", "Tip 2");
          Address addr3 = new Address("C Sokak", "C Sehri", "C Ülkesi", "Tip 3");

          Set<Address> addresses1 = new HashSet<Address>(0);
          addresses1.addAll(Arrays.asList(addr1, addr3));
          Customer cust1 = new Customer("Reis", "Hüseyin AYDIN", addresses1); // Hüseyin Aydın müşterisinin 2 tane adresi var.

          Set<Address> addresses2 = new HashSet<Address>(0);
          addresses2.addAll(Arrays.asList(addr2, addr3));
          Customer cust2 = new Customer("Kardeş", "Ömer Faruk", addresses2); // Ömer Faruk müşterisinin 2 tane adresi var.
          // Aslında burada çoğa çok ilişki var.
          
          
          
          // Müşterileri kaydet. Aynı zamanda ilişkili şekilde kaydoluyor yani ilişkili şekilde veritabanına yansıtılıyor.
          session.save(cust1);
          session.save(cust2);

          session.getTransaction().commit();

          System.out.println("-------------------------------------");

          // Verilerin okunması
          List<Customer> customers = session.createQuery("from Customer").list();

          for (Customer customer : customers) {
              Set<Address> addresses = customer.getAddresses();

              for (Address address : addresses) {
                  System.out.println(customer.getTitle() + " " + customer.getName() + ": "
                          + address.getStreet() + ", " + address.getCity() + ", " + address.getCountry());
              }
          }

          System.out.println("-------------------------------------");
      } catch (Exception e) {
          System.err.println("Hata : " + e);
          session.getTransaction().rollback();
      } finally {
          session.close();
      }
  }
}