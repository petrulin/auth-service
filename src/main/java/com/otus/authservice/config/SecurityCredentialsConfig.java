package com.otus.authservice.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HybridUserDetailsService userDetailsService;


    @Autowired
    DataSource ds;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Value("${ADMIN-SERVER-IP:admin-server}")
    private String adminServer;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // make sure we use stateless session; session won't be used to store user's state.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors()
                .and().csrf().disable()
                // handle an authorized attempts
                //  .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                //  .and()
                // Add a filter to validate user credentials and add token in the response header
                //.addFilterBefore(new CustomCorsFilter(), SecurityContextPersistenceFilter.class )
                // What's the authenticationManager()?
                // An object provided by WebSecurityConfigurerAdapter, used to authenticate the user passing user's credentials
                // The filter needs this auth manager to authenticate the user.

                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/auth-service/api/v1/external/checkHostAccessToken/**").permitAll()
                .antMatchers("/auth-service/**").permitAll()
                .antMatchers("/health/**").permitAll()
                .antMatchers("/health").permitAll()
                .antMatchers("/metrics").permitAll()
                // any other requests must be authenticated
                .anyRequest().authenticated();
    }

    // Spring has UserDetailsService interface, which can be overriden to provide our implementation for fetching user from database (or any other source).
    // The UserDetailsService object is used by the auth manager to load the user from database.
    // In addition, we need to define the password encoder also. So, auth manager can compare and verify passwords.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://smpay-admin-ui.spcom.by, https://smpay-admin-ui.besmart.by"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}