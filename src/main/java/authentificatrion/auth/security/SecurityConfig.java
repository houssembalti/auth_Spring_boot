package authentificatrion.auth.security;

import authentificatrion.auth.filter.CustomAuthentificationfilter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor


public class SecurityConfig extends WebSecurityConfigurerAdapter  {


  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserDetailsService userDetailsService;



  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //changer url login (qui est par defaut) to
    /*  CustomAuthentificationfilter customAuthentificationfilter = new CustomAuthentificationfilter(authenticationManagerBean());
    customAuthentificationfilter.setFilterProcessesUrl("/api/login");*/
    //rq si on exucte ca on me  customAuthentificationfilter dans httpadfilter

    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeHttpRequests().antMatchers("/login").permitAll();
    //http.authorizeHttpRequests().anyRequest().permitAll(); hethi wahadha kenet
    //http.authorizeHttpRequests().antMatchers(GET,"api/user/**").hasAnyAuthority("Role_User");
    //http.authorizeHttpRequests().antMatchers(POST,"api/user/save/**").hasAnyAuthority("Role_Admin");
    http.authorizeHttpRequests().anyRequest().authenticated();
    http.addFilter(new CustomAuthentificationfilter(authenticationManagerBean()));
    http.cors(); //allow origin
    http.addFilterBefore(new CustomAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class);


  }
  @Bean //allow origin
  CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource source = new
      UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean () throws Exception{
    return super.authenticationManagerBean();
  }


}
