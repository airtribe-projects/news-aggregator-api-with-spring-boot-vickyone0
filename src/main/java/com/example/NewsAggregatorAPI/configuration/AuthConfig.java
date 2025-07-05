package com.example.NewsAggregatorAPI.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }
    
    @Bean
    public SecurityFilterChain securityFilterChainAuth(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                    authorizeRequests -> authorizeRequests
                        .requestMatchers("/register","/signin","h2-console")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                        )
                        .formLogin(formLogin -> formLogin.loginPage("/signin") .defaultSuccessUrl("/hello",true).permitAll()
                        );
        return httpSecurity.build();
    }
}
