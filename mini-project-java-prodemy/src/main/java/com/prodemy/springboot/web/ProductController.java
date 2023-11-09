package com.prodemy.springboot.web;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.prodemy.springboot.config.CustomUserDetails;
import com.prodemy.springboot.model.Product;
import com.prodemy.springboot.model.User;
import com.prodemy.springboot.service.ProductService;
import com.prodemy.springboot.service.UserService;
import com.prodemy.springboot.web.dto.ProductDto;
import com.prodemy.springboot.web.dto.UserDto;

import jakarta.annotation.security.RolesAllowed;

@Controller
public class ProductController {
	
	private ProductService productService;
	private UserService userService;
	
	public ProductController(ProductService productService, UserService userService) {
		this.productService = productService;
		this.userService = userService;
	}
	
	@ModelAttribute("product")
	public ProductDto userRegistrationDto() {
		return new ProductDto();
	}

	
	@GetMapping("/admin/product/addProduct")
	public String showFormAddProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "admin/add_product";
	}
	
	@PostMapping("/admin/product/addProduct")
	public String productUpload(@ModelAttribute("product") ProductDto productDto, 
			@RequestParam("mainImg") MultipartFile mainImg,
			@RequestParam(value = "extraImg1", required = false) MultipartFile extraImg1,
			@RequestParam(value = "extraImg2", required = false) MultipartFile extraImg2,
			@RequestParam(value = "extraImg3", required = false) MultipartFile extraImg3
			) throws IOException {
		
		productService.save(productDto, mainImg, extraImg1, extraImg2, extraImg3);
		return "redirect:/admin/product/list";
	}
	
	@GetMapping("admin/product/editProduct/{id}")
	public String showEditProduct(@PathVariable (value = "id") Long id, Model model) {
		Product product = productService.getByProduct(id);
		
		model.addAttribute("product", product);
		return "admin/edit_product";
	}
	
	@PostMapping("admin/product/updateProduct")
	public String updateProduct(@RequestParam Long id, @ModelAttribute("product") ProductDto productDto, 
			@RequestParam("mainImg") MultipartFile mainImg,
			@RequestParam(value = "extraImg1", required = false) MultipartFile extraImg1,
			@RequestParam(value = "extraImg2", required = false) MultipartFile extraImg2,
			@RequestParam(value = "extraImg3", required = false) MultipartFile extraImg3) 
	throws IOException {
		productService.updateProduct(id, productDto, mainImg, extraImg1, extraImg2, extraImg3);
		return "redirect:/admin/product/list";
	}
	
	@GetMapping("/admin/product/list/page/{pageNo}")
	public String getPaginatedAdmin(@PathVariable (value = "pageNo") int pageNo, 
		@RequestParam("sortField") String sortField, @RequestParam(value = "productName", required = false) String productName,
		@RequestParam(value = "minPrice", required = false) Double min, @RequestParam(value = "maxPrice", required = false) Double max,
		@RequestParam("sortDir") String sortDir,
		Model model
		)
	{
		int pageSize = 5;
		
		Page<Product> page = productService.paginatedPage(pageNo, pageSize, sortField, productName, min, max, sortDir);
		List<Product> listProduct = page.getContent() ;
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("productName", productName);
		model.addAttribute("sortDir", sortDir);
		
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listProduct", listProduct);
		
		return "admin/list_product";	
	}
	
	@GetMapping("/user/page/{pageNo}")
	public String getPaginatedUser(@PathVariable (value = "pageNo") int pageNo, 
		@RequestParam("sortField") String sortField, @RequestParam(value = "productName", required = false) String productName,
		@RequestParam(value = "minPrice", required = false) Double min, @RequestParam(value = "maxPrice", required = false) Double max, @RequestParam("sortDir") String sortDir,
		Model model
		)
	{
		int pageSize = 5;
		
		Page<Product> page = productService.paginatedPage(pageNo, pageSize, sortField, productName, min, max, sortDir);
		List<Product> listProduct = page.getContent() ;
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("productName", productName);
		model.addAttribute("sortDir", sortDir);
		
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listProduct", listProduct);
		
		return "customer/dashboard";	
	}
	
	
	@GetMapping("/admin/product/list")
	public String viewAllProduct(Model model) {
		return getPaginatedAdmin(1,"productName",null, null, null,"asc",model);
	}
	
	@GetMapping("/user")
	public String viewAllProductUser(Model model) {
		return getPaginatedUser(1,"productName",null, null, null, "asc",model);
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteUser(@PathVariable (value = "id") Long id, Model model) {
		this.productService.deleteProduct(id);
		return "redirect:/admin/product/list";
	}

}
