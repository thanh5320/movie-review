package com.hust.movie_review.config.security;

import com.hust.movie_review.common.Constants;
import com.hust.movie_review.service.template.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableWebSecurity
@EnableGlobalMethodSecurity(
         securedEnabled = true,
         jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    IUserService userService;

    @Autowired
    private AuthenticationEntryPointHandler unauthorizedHandler;

    @Bean
    public AuthenticationFilter authenticationJwtTokenFilter() {
        return new AuthenticationFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/user/register").permitAll().and()
                .authorizeRequests().antMatchers("/api/review/check-review/**/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/movie/listing").permitAll().and()
                .authorizeRequests().antMatchers("/api/movie/top/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/movie/detail/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/movie/upcoming").permitAll().and()
                .authorizeRequests().antMatchers("/api/movie/update").permitAll().and()
                .authorizeRequests().antMatchers("/api/movie/search").permitAll().and()
                .authorizeRequests().antMatchers("/api/category/listing").permitAll().and()
                .authorizeRequests().antMatchers("/api/category/detail/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/role/listing").permitAll().and()
                .authorizeRequests().antMatchers("/api/role/detail/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/actor/listing").permitAll().and()
                .authorizeRequests().antMatchers("/api/actor/detail/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/review/listing").permitAll()
                .antMatchers("/api/admin/**").hasAnyRole(Constants.ROLE_ADMIN)
//                .antMatchers("/api/admin/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
