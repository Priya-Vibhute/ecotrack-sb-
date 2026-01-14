package com.learn.ecotrack.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.learn.ecotrack.security.jwt.AuthEntryPointJwt;
import com.learn.ecotrack.security.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Autowired
	private AuthEntryPointJwt authEntryPointJwt;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf((csrf) -> csrf.disable())	
        .authorizeHttpRequests((requests) -> requests
        		.requestMatchers("/auth/**").permitAll()
        		.requestMatchers(HttpMethod.POST, "/users/register").permitAll()
        		.anyRequest().authenticated());
        // http.formLogin(withDefaults());
        http.httpBasic(Customizer.withDefaults());
        
        http.exceptionHandling(authentication->
        authentication.authenticationEntryPoint(authEntryPointJwt));

        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
	
	  @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	

}
