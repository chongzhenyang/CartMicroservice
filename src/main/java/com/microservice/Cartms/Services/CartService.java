package com.microservice.Cartms.Services;

import java.util.List;
import java.util.Optional;

import com.microservice.Cartms.model.Cart;


public interface CartService {
	List<Cart> getAllCart();
	Optional<Cart> getSingleCart(Long cartId);
	void createCart(Cart cart);
	void updateCart(Cart cart);
}
