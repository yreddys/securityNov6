package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilter(HttpSecurity http) throws Exception{
		http.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests(
						(requests) -> requests.requestMatchers("/get1", "/get2")
								.authenticated()
								.requestMatchers("/get3", "/get4").permitAll())
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
		UserDetails admin=User.withUsername("admin").password(encoder.encode("admin")).authorities("ADMIN").build();
		UserDetails user=User.withUsername("user").password(encoder.encode("user")).authorities("USER").build();
		 return new InMemoryUserDetailsManager(admin);
		
	}
	
}
