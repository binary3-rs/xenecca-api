package com.xenecca.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.xenecca.api.security.AuthManager;
import com.xenecca.api.security.JwtTokenFilter;
import com.xenecca.api.security.RestAuthenticationEntryPoint;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenFilter _tokenFilter;

	@Autowired
	private RestAuthenticationEntryPoint _authEntryPoint;

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return new AuthManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/api/v1/login", "**/api-docs/**", "/swagger-resources/**", "**/swagger-ui.html",
						"/webjars/**")
				.permitAll().antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v1/courses/{id}/redeem-coupon").permitAll()
				.antMatchers(HttpMethod.POST, "/api/v1/**").authenticated().antMatchers(HttpMethod.DELETE, "/api/v1/**")
				.authenticated().antMatchers(HttpMethod.PUT, "/api/v1/**").authenticated().and().exceptionHandling()
				.authenticationEntryPoint(getAuthEntryPoint()).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(getTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration config = new CorsConfiguration();
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		// config.setAllowCredentials(true);
//		config.addAllowedOrigin("*");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		source.registerCorsConfiguration("/api/v1/**", config);
//		return source;
//	}

}
