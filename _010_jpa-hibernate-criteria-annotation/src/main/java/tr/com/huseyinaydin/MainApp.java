package tr.com.huseyinaydin;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			// Müşteri kayıtlarını veritabanına ekledik.
			add();

			// Kriterli sorgular için Criteria nesne örneğini oluşturduk.
			Criteria cr = session.createCriteria(Product.class);
			
			// Kriterli sorguya kısıtlamalar getiriyoruz.
			// Sorgu, miktarı 10'dan büyük olan veya ürün adının başında 'Bo' geçen ifadeyi getirir.
			cr.add(Restrictions.or(Restrictions.gt("quantity", 10), Restrictions.ilike("productName", "Bo%")));
			
			// Sayfalama : kriterli sorunun sonucu 2 olacaktır yani sorgudan gelen kayıtların ilk 2'sini al dedik. 
			cr.setMaxResults(2);
			
			// Sıralama : Kayıtları miktar kolonuna göre ASC yani küçükten büyüğe göre sıraladık.
			cr.addOrder(Order.asc("quantity"));
			
			List<Product> result = cr.list();
			
			for (Product p : result) {
				System.out.println(p.toString());
			}

			// Kriterli sorgu oluşturduk.
			Criteria crTot = session.createCriteria(Product.class);
			
			// Birim fiyatı en fazla olanı getirmesi için projection ekledik.
			crTot.setProjection(Projections.max("unitPrice"));
			
			List<Product> maxPrice = crTot.list(); //sorguyu çalıştırıp sonucunu aldık.
			System.out.println("En fazla fiyat: " + maxPrice.get(0));

		} catch (Exception e) {
			System.err.println("Müşteri kaydı yapılırken hata oldu : " + e);
		} finally {
			session.close();
		}
	}

	public static void add() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			session.save(new Product("Kitap", 5, new Float(35.45)));
			session.save(new Product("Kalem", 12, new Float(10.00)));
			session.save(new Product("Silgi", 3, new Float(5.30)));
			session.save(new Product("UrunX", 6, new Float(18.00)));
			session.save(new Product("UrunY", 10, new Float(36.00)));
			t.commit();
		} catch (Exception e) {
			System.err.println("Ekleme esnasında hata : " + e);
			t.rollback();
		} finally {
			session.close();
		}
	}
}