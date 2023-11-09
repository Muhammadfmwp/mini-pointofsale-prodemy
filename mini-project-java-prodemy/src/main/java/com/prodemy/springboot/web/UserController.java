package com.prodemy.springboot.web;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prodemy.springboot.web.dto.UserDto;

import jakarta.annotation.security.RolesAllowed;

import com.prodemy.springboot.model.User;
import com.prodemy.springboot.service.UserService;

@Controller
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserDto userRegistrationDto() {
		return new UserDto();
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	

	@GetMapping("/admin/listUser/page/{pageNo}")
	public String getPaginated(@PathVariable (value = "pageNo") int pageNo, 
		@RequestParam("sortField") String sortField,
		@RequestParam("sortDir") String sortDir,
		Model model
		)
	{
		int pageSize = 5;
		
		Page<User> page = userService.paginatedPage(pageNo, pageSize, sortField, sortDir);
		List<User> listUser = page.getContent() ;
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listUser", listUser);
		
		return "admin/list_user";	
	}
	
	
	@GetMapping("/admin/listUser")
	public String viewAllUser(Model model) {
		return getPaginated(1,"userName","asc",model);
	}
	
	@GetMapping("/register")
	public String showRegisterForm() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUserAccount(@ModelAttribute("user") UserDto userRegistrationDto) {
		userService.save(userRegistrationDto);
		return "redirect:/register?success";
	}
	
	@PostMapping("/admin/updateUser")
	public String updateUserAccount(@RequestParam Long id, @ModelAttribute("user") UserDto userRegistrationDto) {
		userService.updateUser(id,userRegistrationDto);
		return "redirect:/admin/listUser?updated";
	}
	
	@GetMapping("/admin/editUser/{id}")
	public String showEditMahasiswa(@PathVariable (value = "id") Long id, Model model) {
		User user = userService.getByUser(id);
		
		model.addAttribute("user", user);
		return "admin/edit_user";
	}
	
	@GetMapping("/admin/profile")
	public String showProfileAdmin(Principal principal, Model model) {
		User user = userService.getProfile(principal);
		model.addAttribute("user", user);
		return "profile";
	}
	
	@GetMapping("/admin/update-password")
	public String updatePasswordAdmin() {
		return "update_password";
	}
	
	@GetMapping("/admin/updatePassword")
	public String updatePasswordAdmin(Principal principal,  @ModelAttribute("user") UserDto userDto) {
		userService.updatePassword(principal, userDto);
		return "profile?success";
	}
	
	
	@GetMapping("/user/profile")
	public String showProfileUser(Principal principal, Model model) {
		User user = userService.getProfile(principal);
		model.addAttribute("user", user);
		return "profile";
	}
	
	@GetMapping("/user/update-password")
	public String updatePasswordUser() {
		return "update_password";
	}
	
	@GetMapping("/user/updatePassword")
	public String updatePassword(Principal principal,  @ModelAttribute("user") UserDto userDto) {
		userService.updatePassword(principal, userDto);
		return "profile?success";
	}
	

	
	@GetMapping("/admin/deleteUser/{id}")
	public String deleteUser(@PathVariable (value = "id") Long id, Model model) {
		this.userService.deleteUser(id);
		return "redirect:/admin/listUser?deleted";
	}
}
