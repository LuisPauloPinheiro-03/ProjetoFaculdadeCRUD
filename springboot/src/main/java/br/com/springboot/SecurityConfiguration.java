package br.com.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("12345")) 
            .roles("ADMINISTRADOR")
            .build();
        
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder.encode("12345"))
            .roles("USUARIO")
            .build();
        
        return new InMemoryUserDetailsManager(admin, user);
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers(HttpMethod.GET, "/nota-entrada").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.GET, "/nota-saida").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.GET, "/estoque").hasRole("ADMINISTRADOR")
                .anyRequest().authenticated()
            )
            
            .formLogin((form) -> form
                .loginPage("/login") 
                .permitAll() 
            )
            
            .logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.GET.name()))
                .logoutSuccessUrl("/login")
                .permitAll()
            );

        return http.build();
    }
}
