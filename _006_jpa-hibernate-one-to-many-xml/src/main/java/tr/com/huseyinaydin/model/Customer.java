package tr.com.huseyinaydin.model;

import java.util.HashSet;
import java.util.Set;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Hibernate.
* 
*/

public class Customer {

	private int customerId;
	private String title;
	private String name;
	private Set<Orders> orders = new HashSet<Orders>(0);
	/*
	   <set name="orders" table="ORDERS" inverse="true" lazy="true" fetch="select">
			<key>
				<column name="CUSTOMER_ID" not-null="true" />
			</key>
			<one-to-many class="tr.com.huseyinaydin.model.Orders" />
		</set>
	 */

	public Customer() {
	}

	public Customer(String title, String name) {
		this.title = title;
		this.name = name;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}
}