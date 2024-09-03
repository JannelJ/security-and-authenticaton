package org.northcoders.security_and_authentication.security;


import org.northcoders.security_and_authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/h2-console/**").permitAll();
                    auth.requestMatchers("/api/v1/open/greeting").permitAll();
                    auth.requestMatchers("/api/v1/protected/greeting").authenticated();
                    auth.anyRequest().authenticated();
                })

//                .oauth2Login(withDefaults());
                .oauth2Login(o -> {
                    o.userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(userService));
                });



        http.headers(headers -> headers.frameOptions(withDefaults()).disable());

        return http.build();
    }


}
