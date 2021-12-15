package com.example.springsecurity.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.example.springsecurity.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		  http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().
		  httpBasic() .authenticationEntryPoint(entryPoint()).and().sessionManagement()
		  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		  .and()
	        .anonymous().disable()
	        .exceptionHandling()
	        .authenticationEntryPoint(new MyAuthenticationEntryPoint());
		 
	}

	@Bean
	public AuthenticationEntryPoint entryPoint() {
		return new BasicAuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException {
				JSONObject jsonObject = new JSONObject();
				try {
					response.addHeader("WWW-Authenticate", "Basic Realm - " + getRealmName());
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.setContentType("application/json");
					response.getWriter()
							.println(jsonObject.put("exception", "HTTP Status 401 - " + authException.getMessage()));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void afterPropertiesSet() {
				setRealmName("almightyjava");
				super.afterPropertiesSet();
			}
		};
	}
}
