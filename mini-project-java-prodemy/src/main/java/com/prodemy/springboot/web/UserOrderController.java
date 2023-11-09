package com.prodemy.springboot.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prodemy.springboot.model.Payment;
import com.prodemy.springboot.model.Product;
import com.prodemy.springboot.model.Shipping;
import com.prodemy.springboot.model.UserOrder;
import com.prodemy.springboot.repository.PaymentRepository;
import com.prodemy.springboot.repository.ShippingRepository;
import com.prodemy.springboot.service.UserOrderService;
import com.prodemy.springboot.web.dto.UserOrderDto;

import jakarta.annotation.security.RolesAllowed;

@Controller
public class UserOrderController {
	
	@Autowired
	private UserOrderService userOrderService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ShippingRepository shippingRepository;
	
	@ModelAttribute("order")
	public UserOrderDto userOrderDto() {
		return new UserOrderDto();
	}
	
	@GetMapping("/user/addToCart/{id}")
	public String addToCart(@PathVariable (value = "id") Long id, Principal principal) {
		userOrderService.addtoCart(id, principal);
		return "redirect:/user?addedtocart";
	}
	
	@GetMapping("/admin/product/addToCart/{id}")
	public String addToCartAdmin(@PathVariable (value = "id") Long id, Principal principal) {
		userOrderService.addtoCart(id, principal);
		return "redirect:/admin/product/list?addedtocart";
	}
	
	
	@GetMapping("/user/cart")
	public String listCart(Model model, Principal principal) {
		UserOrder cart =  userOrderService.getCartByUser(principal);
		model.addAttribute("cart", cart);

		List<Payment> payment = paymentRepository.findAll();
		List<Shipping> shipping = shippingRepository.findAll();
		model.addAttribute("paymentList", payment);
		model.addAttribute("shippingList", shipping);
		
		return "user/cart";
	}
	
	@GetMapping("/admin/cart")
	public String listCartAdmin(Model model, Principal principal) {
		UserOrder cart =  userOrderService.getCartByUser(principal);
		model.addAttribute("cart", cart);

		List<Payment> payment = paymentRepository.findAll();
		List<Shipping> shipping = shippingRepository.findAll();
		model.addAttribute("paymentList", payment);
		model.addAttribute("shippingList", shipping);
		return "admin/cart";
	}
	
	@GetMapping("/user/history")
	public String listHistory(Model model, Principal principal) {
		List<UserOrder> listOrder = userOrderService.getHistoryByUser(principal);
		model.addAttribute("listOrder", listOrder);
		return "user/history";
	}
	
	@GetMapping("/admin/history")
	public String listHistoryAdmin(Model model, Principal principal) {
		List<UserOrder> listOrder = userOrderService.getHistoryByUser(principal);
		model.addAttribute("listOrder", listOrder);
		return "admin/history";
	}
	
	
	@PostMapping("/user/checkoutOrder")
	public String checkout(Long id, @ModelAttribute("order") UserOrderDto userOrderDto) {
		userOrderService.checkout(id, userOrderDto);
		return "redirect:/user/history";
	}
	
	@PostMapping("/admin/checkoutOrder")
	public String checkoutAdmin(Long id, @ModelAttribute("order") UserOrderDto userOrderDto) {
		userOrderService.checkout(id, userOrderDto);
		return "redirect:/admin/history";
	}
	
	@GetMapping("/user/cancelOrder/{id}")
	public String cancelOrder(@PathVariable("id") Long id) {
		userOrderService.cancel(id);
		return "redirect:/user/cart";
	}
	
	@GetMapping("/admin/cancelOrder/{id}")
	public String cancelOrderAdmin(@PathVariable("id") Long id) {
		userOrderService.cancel(id);
		return "redirect:/admin/cart";
	}


}
