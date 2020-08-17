package com.alexcode.eatgo.security;

import com.alexcode.eatgo.jwt.JwtConfig;
import com.alexcode.eatgo.jwt.JwtSecretKey;
import com.alexcode.eatgo.jwt.JwtTokenVerifier;
import com.alexcode.eatgo.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.alexcode.eatgo.security.ApplicationUserRole.ADMIN;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

  private final ApplicationUserService applicationUserService;
  private final PasswordEncoder passwordEncoder;
  private final JwtConfig jwtConfig;
  private final JwtSecretKey jwtSecretKey;

  @Autowired
  public ApplicationSecurityConfig(
          ApplicationUserService applicationUserService,
          PasswordEncoder passwordEncoder,
          JwtConfig jwtConfig,
          JwtSecretKey jwtSecretKey) {

    this.applicationUserService = applicationUserService;
    this.passwordEncoder = passwordEncoder;
    this.jwtConfig = jwtConfig;
    this.jwtSecretKey = jwtSecretKey;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .cors().disable()
            .csrf().disable()
            .formLogin().disable()
            .headers().frameOptions().disable()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, jwtSecretKey))
            .addFilterAfter(new JwtTokenVerifier(jwtConfig, jwtSecretKey, applicationUserService), JwtUsernameAndPasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/management/api/v1/**").hasRole(ADMIN.name())
            .anyRequest().authenticated();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(applicationUserService);

    return provider;
  }

}
