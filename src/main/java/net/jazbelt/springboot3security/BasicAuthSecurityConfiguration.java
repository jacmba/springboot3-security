package net.jazbelt.springboot3security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthSecurityConfiguration {

    @SuppressWarnings("removal")
    @Bean
    SecurityFilterChain secFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( auth -> {
            auth.anyRequest().authenticated();
        });
        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        http.httpBasic();

        http.csrf().disable();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailService() {
        UserDetails user = User.withUsername("fakeuser")
                .password("{noop}dummypass")
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("fakeadmin")
                .password("{noop}dummypass")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
