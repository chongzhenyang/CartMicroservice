package com.microservice.Cartms.Services;

import java.util.List;
import java.util.Optional;

import com.microservice.Cartms.model.Cart;
import com.microservice.Cartms.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class CartServicesImpl implements CartService {
	private static final Logger LOGGER=LoggerFactory.getLogger(CartServicesImpl.class);
	private final CartRepository cartRepository;
	
	
	public CartServicesImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public List<Cart> getAllCart() {
		LOGGER.info("*** I am in Get All carts Method ***");
		
		return cartRepository.findAll();
	}

	@Override
	public Optional<Cart> getSingleCart(Long cartId) {
		LOGGER.info("*** I am in Get cart By Id Method ***");
		return cartRepository.findById(cartId);
	}

	@Override
	public void createCart(Cart cart) {
		cartRepository.save(cart);
	}

	@Override
	public void updateCart(Cart cart) {
		cartRepository.deleteById(cart.getCartId());
		cartRepository.save(cart);
	}




	


}