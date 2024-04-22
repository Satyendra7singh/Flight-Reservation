package com.satyendra.flightreservation.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
public class WebSecurityConfig{
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	SecurityContextRepository securityCotextRepo() {
		return new DelegatingSecurityContextRepository(new RequestAttributeSecurityContextRepository(),new HttpSessionSecurityContextRepository());
	}
	
	@Bean
	AuthenticationManager authManager() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(bcryptPasswordEncoder());
		return new ProviderManager(authProvider);
	}
	
	
	@Bean
	BCryptPasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http.authorizeRequests().requestMatchers("admin/showAddFlight").hasRole("ADMIN")
		.requestMatchers("/showCompleteReservation*","findflights","/completeReservation","reservationConfirmation")
		.authenticated()
		.requestMatchers("/showReg","/login/registerUser","/registerUser","/showLogin","/login/login","/login","findflights"
				,"/","index.html","/reservation/*").permitAll().and().csrf().disable()
		.securityContext((securityContext)->securityContext.requireExplicitSave(true))
		.build();
		
	}

	

}
