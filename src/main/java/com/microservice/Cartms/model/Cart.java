package com.microservice.Cartms.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Cart {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long cartId;

	private String customerName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	private List<Product> products;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
