/*
 * package com.leonardolelli.LibraryGateway.security;
 * 
 * import org.springframework.context.annotation.Configuration; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * http.csrf().disable().authorizeRequests() .antMatchers(HttpMethod.GET,
 * "/api/catalog/**").permitAll() .antMatchers(HttpMethod.GET,
 * "/api/rent/**").permitAll() .antMatchers(HttpMethod.GET,
 * "/api/review/**").permitAll() .antMatchers(HttpMethod.POST,
 * "/api/rent/**").permitAll() .antMatchers(HttpMethod.PATCH,
 * "/api/rent/**").permitAll() .anyRequest() .authenticated();
 * 
 * }
 * 
 * }
 */