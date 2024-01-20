package tr.com.huseyinaydin;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tr.com.huseyinaydin.model.Product;
import tr.com.huseyinaydin.util.HibernateUtil;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Hibernate.
* 
*/

public class MainApp {
	static Logger log = Logger.getLogger(AppMain.class.getName());

	public static void main(String[] args) {
		
		// 3 ürünü database kaydet
		create();

		// tüm ürünleri databaseden oku.
		read();

		// 2. ürünü güncelle
		update();

		// 3. ürünü sil
		delete();
		
		// Tüm ürün kayıtlarını other_product tablosuna kaydet.
		insert();

		HibernateUtil.shutdown();
	}

	public static void create() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			Product product1 = new Product("Kitap", 1, 20.34F, false, new Date());
			Product product2 = new Product("Kalem", 2, 15.01F, false, new Date());
			Product product3 = new Product("Çanta", 1, 98.44F, false, new Date());
			session.save(product1);
			session.save(product2);
			session.save(product3);
			t.commit();
		} catch (Exception e) {
			log.error("Kaydetme esnasında hata : ", e);
			t.rollback();
		}
		session.close();
	}

	public static void read() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from Product where unitPrice < :p_unitPrice");
			query.setParameter("p_unitPrice", 50.00);
			List<Product> result = query.list();
			for (Product p : result) {
				System.out.println(p.getProductName() + ", " + p.getQuantity() + ", " + p.getUnitPrice() + ", "
						+ p.getDiscontinued() + ", " + p.getModifiedDate());
			}
		} catch (Exception e) {
			log.error("Okuma esnasında hata : ", e);
		}
		session.close();
	}

	public static void update() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			Query query = session.createQuery(
					"update Product set quantity=:p_quantity, discontinued=:p_discontinued where productName=:p_productName");
			query.setParameter("p_quantity", 3);
			query.setParameter("p_discontinued", true);
			query.setParameter("p_productName", "Kalem");
			int result = query.executeUpdate();
			System.out.println(result + " kayıtlar güncellendi");
			t.commit();
		} catch (Exception e) {
			log.error("Güncelleme esnasında hata : ", e);
			t.rollback();
		}
		session.close();
	}

	public static void delete() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			Query query = session.createQuery("delete from Product where productName=:productName");
			query.setParameter("productName", "Satchel");
			int result = query.executeUpdate();
			System.out.println(result + " satır güncellendi");
			t.commit();
		} catch (Exception e) {
			log.error("Silme esneasında hata : ", e);
			t.rollback();
		}
		session.close();
	}


	public static void insert() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			Query query = session.createQuery(
					"insert into OtherProduct (productName, quantity, modifiedDate) select p.productName, p.quantity, p.modifiedDate from Product p");
			int result = query.executeUpdate();
			System.out.println(result + " satır girildi(inserted)");
			t.commit();
		} catch (Exception e) {
			log.error("Kayıt girilme esnasında hata : ", e);
			t.rollback();
		}
		session.close();
	}
}