package org.umeframework.cloud;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * WebSecurityConfig
 * 
 * @author UME team
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF protection to let Eureka client register
        // http.csrf().disable().authorizeRequests().anyRequest().permitAll();
        http.csrf().disable();
    }
}
