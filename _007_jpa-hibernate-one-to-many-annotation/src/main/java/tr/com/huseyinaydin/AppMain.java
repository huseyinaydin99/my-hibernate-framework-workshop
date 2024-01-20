package tr.com.huseyinaydin;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import tr.com.huseyinaydin.model.Customer;
import tr.com.huseyinaydin.model.Orders;
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

            // Bir müşteri kaydı
            Customer customer1 = new Customer("Reis", "Hüseyin AYDIN");

            // O müşteriye ait birden fazla sipariş
            Orders order1 = new Orders("001", Float.parseFloat("100.1"), new Date());
            Orders order2 = new Orders("002", Float.parseFloat("200.4"), new Date());
            Orders order3 = new Orders("003", Float.parseFloat("300.7"), new Date());

            // Müşterinin siparişleri ekleniyor.
            customer1.getOrders().add(order1);
            customer1.getOrders().add(order2);
            customer1.getOrders().add(order3);

            // Siparişlerin müşterisi set ediliyor yani ekleniyor.
            order1.setCustomer(customer1);
            order2.setCustomer(customer1);
            order3.setCustomer(customer1);

            // Müşteri güzelce kaydediliyor.
            session.save(customer1);

            // Siparişler kaydediliyor.
            session.save(order1);
            session.save(order2);
            session.save(order3);

            //-------------------------------------------------------------				

            Customer customer2 = new Customer("Hacı", "Ahmet");

            Orders order4 = new Orders("code4", Float.parseFloat("250.4"), new Date());
            Orders order5 = new Orders("code5", Float.parseFloat("750.1"), new Date());
            Orders order6 = new Orders("code6", Float.parseFloat("240.9"), new Date());

            customer2.getOrders().add(order4);
            customer2.getOrders().add(order5);
            customer2.getOrders().add(order6);

            order4.setCustomer(customer2);
            order5.setCustomer(customer2);
            order6.setCustomer(customer2);

            session.save(customer2);

            session.save(order4);
            session.save(order5);
            session.save(order6);

            session.getTransaction().commit();

            System.out.println("-------------------------------------");
            // Verilerin okunması
            List<Customer> customers = session.createQuery("from Customer").list();
            for (Customer customer : customers) {
                Set<Orders> orders = customer.getOrders();

                for (Orders order : orders) {
                    System.out.println(customer.getCustomerId() + " " + customer.getTitle() + " " + customer.getName() + ": "
                            + order.getCode() + ", " + order.getAmount() + ", " + order.getOrderDate()
                            + ", " + order.getOrderId() + ", " + order.getCustomer().getCustomerId());
                }
            }
            System.out.println("-------------------------------------");
        } catch (Exception e) {
            System.err.println("Hata :" + e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}