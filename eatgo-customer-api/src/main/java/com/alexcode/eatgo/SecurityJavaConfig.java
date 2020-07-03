package com.alexcode.eatgo;

import com.alexcode.eatgo.filters.JwtAuthenticationFilter;
import com.alexcode.eatgo.security.ApplicationUserService;
import com.alexcode.eatgo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.Filter;

import static com.alexcode.eatgo.security.ApplicationUserRole.CUSTOMER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

  private final ApplicationUserService applicationUserService;

  @Autowired
  public SecurityJavaConfig(ApplicationUserService applicationUserService) {
    this.applicationUserService = applicationUserService;
  }

  @Value("${jwt.secret}")
  private String secret;  // 해싱에 사용할 Secret Number

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtUtil jwtUtil() {
    return new JwtUtil(secret);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    Filter filter = new JwtAuthenticationFilter(authenticationManager(), jwtUtil());

    http
            .cors().disable()
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests()
            .antMatchers("/customer/api/**").hasRole(CUSTOMER.name())
            .and()
            .httpBasic();
//            .formLogin().disable()
//            .and()
//            .addFilter(filter)
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(applicationUserService);

    return provider;
  }

}
