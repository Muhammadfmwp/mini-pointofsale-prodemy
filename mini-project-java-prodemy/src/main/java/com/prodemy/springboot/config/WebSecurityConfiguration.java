package com.prodemy.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.prodemy.springboot.service.UserService;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	
	@Autowired
	private CustomLoginSuccessHandler customLoginSuccessHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(customUserDetailsService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
	throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	 @Bean
	    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 		http
		 			.authorizeHttpRequests(auth -> auth
		 				.requestMatchers("/login","/register","/js/**","/css/**")
			 			.permitAll()
			 			.requestMatchers("/admin/**").hasAuthority("Admin")
		 				.requestMatchers("/user/**").hasAuthority("Customer")
				 		.anyRequest().authenticated())
		 			.formLogin(form -> form
		 					.loginPage("/login")
		 					.loginProcessingUrl("/login")
		 					.successHandler(customLoginSuccessHandler)
		 					.failureUrl("/login?error")
		 					.permitAll()
		 					)
		 			.logout( logout -> logout
		 					.invalidateHttpSession(true).clearAuthentication(true)
		 					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
		 					)
		 			.httpBasic().and()
		 		      .csrf().disable();
	        return http.build();
	    }

	

}
