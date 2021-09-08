package minhhn.blog.config;

import minhhn.blog.authentication.JwtAuthenticationEntryPoint;
import minhhn.blog.authentication.JwtRequestFilter;
import minhhn.blog.exception.filter.FilterChainExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final UserDetailsService jwtUserDetailsService;
  private final JwtRequestFilter jwtRequestFilter;
  private final FilterChainExceptionHandler exceptionHandlerFilter;

  public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                        UserDetailsService jwtUserDetailsService,
                        JwtRequestFilter jwtRequestFilter,
                        FilterChainExceptionHandler exceptionHandlerFilter) {
    this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    this.jwtUserDetailsService = jwtUserDetailsService;
    this.jwtRequestFilter = jwtRequestFilter;
    this.exceptionHandlerFilter = exceptionHandlerFilter;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // configure AuthenticationManager so that it knows from where to load
    // user for matching credentials
    // Use BCryptPasswordEncoder
    auth.userDetailsService(jwtUserDetailsService).passwordEncoder(this.passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/authenticate").permitAll()
        .mvcMatchers(HttpMethod.POST, "/api/users/register").permitAll()
        .mvcMatchers(HttpMethod.GET, "/api/posts", "api/posts/*").permitAll()
        .mvcMatchers(HttpMethod.GET, "/api/categories/with-sub").permitAll()
        .anyRequest().authenticated()
        .and().exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(this.exceptionHandlerFilter, LogoutFilter.class);
  }

}
