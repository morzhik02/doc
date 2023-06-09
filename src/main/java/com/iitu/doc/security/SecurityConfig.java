package com.iitu.doc.security;

import com.iitu.doc.filter.CustomAuthenticationFilter;
import com.iitu.doc.filter.CustomAuthorizationFilter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

//@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    UserDetailsService userDetailsService;
    BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/home").permitAll();
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.cors().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.authorizeRequests()
                .antMatchers(GET, "/api/login/**",  "/api/token/refresh/**").permitAll();
        http.authorizeRequests()
                .antMatchers(POST, "/login/**").permitAll();
//                .antMatchers(GET, "/api/user/**").hasAnyAuthority("ADMIN")
//                .antMatchers(POST, "/api/user/save/**").hasAnyAuthority("ADMIN")
//                .antMatchers(POST, "/api/role/save/**").hasAnyAuthority("ADMIN")
//                .antMatchers(POST, "/api/doc/**").hasAnyAuthority("ADMIN");
//                .antMatchers(GET, "/api/home/**").permitAll()
//                .anyRequest().authenticated();

//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
//        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
//        http.csrf().disable();
//        http.cors().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().antMatchers(GET, "/api/login/**",  "/api/token/refresh/**").permitAll();
//        http.authorizeRequests().antMatchers(GET, "/api/user/**").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(POST, "/api/user/save/**").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(POST, "/api/role/save/**").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(POST, "/api/doc/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();

        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/**", "/api/v1/api-docs/**", "/api/auth/register",
                "/api/auth/login", "/actuator/**", "/api/cities", "/api/districts/**");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
