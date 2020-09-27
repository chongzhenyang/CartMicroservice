package com.microservice.Cartms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.microservice.Cartms.model.Cart;
import com.microservice.Cartms.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.Cartms.Services.CartService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CartController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CartController.class);
	private final CartService cartService;
	private final RestTemplate restTemplate;

	public CartController(CartService cartService, RestTemplate restTemplate) {
		this.cartService = cartService;
		this.restTemplate = restTemplate;
	}

	@GetMapping("/carts")
	public ResponseEntity getAllCarts()
	{ LOGGER.info("*** I am in Get All Carts Method of Controller Class ***");
		return ResponseEntity.ok(cartService.getAllCart());
	}

	
	@GetMapping("/carts/{cartId}")
	public ModelAndView getSingleCart(@PathVariable Long cartId)
	{LOGGER.info("*** I am in Get cart By Id Method In controller class ***");
		Optional<Cart> singleCart= cartService.getSingleCart(cartId);
		ModelAndView modelAndView = new ModelAndView("cart");
		modelAndView.addObject("cart", singleCart.get());
		return modelAndView;
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public Product addProductToCart(@RequestBody Product product){
		Cart temp = cartService.getSingleCart((long) 1).get();
		temp.getProducts().add(product);
		cartService.createCart(temp);
		return product;
	}

	@RequestMapping(value = "/deleteProduct/{productId}", method = RequestMethod.GET)
	public ModelAndView deleteProductFromCart(@PathVariable Long productId){
		Cart temp = cartService.getSingleCart((long) 1).get();
		temp.getProducts().removeIf(product -> product.getProductId() == productId);
		cartService.createCart(temp);
		ModelAndView modelAndView = new ModelAndView("cart");
		modelAndView.addObject("cart", temp);
		return modelAndView;
	}

	@RequestMapping("/success/{cartId}")
	public ModelAndView successPayment(@PathVariable long cartId){
		List<Integer> list = new ArrayList<>();
		Cart cart = cartService.getSingleCart(cartId).get();
		for(int i = 0; i < cart.getProducts().size(); i++){
			list.add(cart.getProducts().get(i).getPrice());
		}
		ResponseEntity<List> responseEntity = restTemplate.postForEntity("http://paymentms/success", list, List.class);
		ModelAndView modelAndView = new ModelAndView("success");
		modelAndView.addObject("cart", cart);
		return modelAndView;
	}
}
