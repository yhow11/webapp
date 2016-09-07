package com.inctool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.inctool.config.ServiceContext;

@Configuration
@EnableWebSecurity
public class SecurityContext extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ServiceContext servicesContext;
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                //Spring Security ignores request to static resources such as CSS or JS files.
                .ignoring()
                    .antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception  {
    	http
        .csrf().disable();
//	    	http
//	        .csrf().disable();
//			http.authorizeRequests()
//			.and()
//			.formLogin()
//			        .loginPage("/login")
//			        .loginProcessingUrl("/process/login")
//			        .failureUrl("/login")
//			        .passwordParameter("password")
//			        .usernameParameter("username").defaultSuccessUrl("/app");
    }

    /**
     * Configures the authentication manager bean which processes authentication
     * requests.
     * @throws Exception 
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	 auth
//         .userDetailsService(servicesContext.userDetailsService())
//         .passwordEncoder(passwordEncoder());
    }

    /**
     * This is used to hash the password of the user.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    
}
