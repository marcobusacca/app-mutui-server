package com.advancia.spring.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.advancia.spring.auth.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // http.csrf(csrf -> csrf.disable())
        // .authorizeHttpRequests(requests -> requests
        // .requestMatchers("/api/**").permitAll());

        // return http.build();

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req.requestMatchers("/api/login/**", "/api/register/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .userDetailsService(userDetailsService)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // @Bean
    // UserDetailsService userDetailsService() {
    // return new UserService();
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // @Bean
    // DaoAuthenticationProvider authenticationProvider() {

    // DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    // authProvider.setUserDetailsService(userDetailsService());
    // authProvider.setPasswordEncoder(passwordEncoder());

    // return authProvider;
    // }

    // @Bean
    // FilterRegistrationBean<CorsFilter> getCorsSettings() {

    // final CorsConfiguration config = new CorsConfiguration();

    // // OPTIONS
    // // config.setAllowCredentials(true);

    // config.addAllowedOrigin("http://localhost:4200"); // DEVELOP FE SERVER

    // // HEADERS
    // config.addAllowedHeader("Content-Type");
    // config.addAllowedHeader("Authorization");
    // config.addAllowedHeader("X-XSRF-TOKEN");
    // config.addAllowedHeader("Accept");

    // // METHODS
    // config.addAllowedMethod(HttpMethod.GET);
    // config.addAllowedMethod(HttpMethod.POST);
    // config.addAllowedMethod(HttpMethod.PUT);
    // config.addAllowedMethod(HttpMethod.DELETE);

    // // SET CONFIG ON PATHS
    // final UrlBasedCorsConfigurationSource source = new
    // UrlBasedCorsConfigurationSource();
    // source.registerCorsConfiguration("/**", config);

    // final FilterRegistrationBean<CorsFilter> bean = new
    // FilterRegistrationBean<>(new CorsFilter(source));
    // bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

    // return bean;
    // }
}
