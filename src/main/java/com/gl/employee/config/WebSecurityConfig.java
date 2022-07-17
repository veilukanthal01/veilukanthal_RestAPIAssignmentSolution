package com.gl.employee.config;

import com.gl.employee.service.impl.ApplicationUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new ApplicationUserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/roles/create").permitAll()
                .antMatchers("/users/register").permitAll()
                .antMatchers("/api/employees/save").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/api/employees/getAllEmployees").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/api/employees/getEmployeeById/**").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/api/employees/search/**").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/api/employees/update").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/api/employees/delete/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and().httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");

    }
}
