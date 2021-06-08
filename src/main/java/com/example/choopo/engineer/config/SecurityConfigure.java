package com.example.choopo.engineer.config;

import com.example.choopo.engineer.filter.TokenRequestFilter;
import com.example.choopo.engineer.service.TemporaryTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableScheduling
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private TemporaryTokenService temporaryTokenService;

    @Autowired
    private TokenRequestFilter tokenRequestFilter;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(temporaryTokenService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/login", "/register", "/reference/user-type/")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/article/**", "/")
                .permitAll()
                .antMatchers("/refrernce/**", "/body", "/category", "/topic",  "/user")
                .hasAnyAuthority("ADMIN", "WRITER")
                .antMatchers(HttpMethod.POST, "/admin/article/")
                .hasAnyAuthority("WRITER")
                .antMatchers(HttpMethod.PUT, "/admin/article/**", "/admin/article/nonactive/reactive/**")
                .hasAnyAuthority("WRITER")
                .antMatchers(HttpMethod.DELETE, "admin/article/nonactive/**")
                .hasAnyAuthority("WRITER")
                .antMatchers(HttpMethod.PUT, "/admin/article/authorized/**", "/admin/article/takedown/reactivate/**")
                .hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "admin/article/takedown/**")
                .hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/admin/article/**")
                .hasAnyAuthority("ADMIN", "WRITER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(tokenRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return  super.authenticationManagerBean();
    }
}
