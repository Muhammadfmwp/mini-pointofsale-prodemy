package com.prodemy.springboot.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.onAuthenticationSuccess(request, response, authentication);
	}

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String tagetUrl = determineTargetUrl(authentication);
		
		if(response.isCommitted()) {
			return;
		}
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, tagetUrl);
	}

	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		// TODO Auto-generated method stub
		return super.determineTargetUrl(request, response, authentication);
	}

	
	protected String determineTargetUrl(Authentication authentication) {
		String url ="/login?error";
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for(GrantedAuthority a: authorities) {
			roles.add(a.getAuthority());
		}
		
		if(roles.contains("Admin")) {
			url = "/admin";
		}
		else if(roles.contains("Customer")) {
			url = "/user";
		}
		
		return url;
	}

	@Override
	public void setDefaultTargetUrl(String defaultTargetUrl) {
		// TODO Auto-generated method stub
		super.setDefaultTargetUrl(defaultTargetUrl);
	}

	@Override
	public void setAlwaysUseDefaultTargetUrl(boolean alwaysUseDefaultTargetUrl) {
		// TODO Auto-generated method stub
		super.setAlwaysUseDefaultTargetUrl(alwaysUseDefaultTargetUrl);
	}

	@Override
	protected boolean isAlwaysUseDefaultTargetUrl() {
		// TODO Auto-generated method stub
		return super.isAlwaysUseDefaultTargetUrl();
	}

	@Override
	public void setTargetUrlParameter(String targetUrlParameter) {
		// TODO Auto-generated method stub
		super.setTargetUrlParameter(targetUrlParameter);
	}

	@Override
	protected String getTargetUrlParameter() {
		// TODO Auto-generated method stub
		return super.getTargetUrlParameter();
	}

	@Override
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		// TODO Auto-generated method stub
		super.setRedirectStrategy(redirectStrategy);
	}

	@Override
	protected RedirectStrategy getRedirectStrategy() {
		// TODO Auto-generated method stub
		return super.getRedirectStrategy();
	}

	@Override
	public void setUseReferer(boolean useReferer) {
		// TODO Auto-generated method stub
		super.setUseReferer(useReferer);
	}

}
