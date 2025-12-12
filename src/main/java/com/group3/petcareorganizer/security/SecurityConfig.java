package com.group3.petcareorganizer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import com.group3.petcareorganizer.service.OwnerService;


import java.util.Properties;

/*
   @Configuration means this is a configuration class that will use methods that are annotated with
   @Bean to register Spring Beans
   @EnableWebSecurity means this class uses Spring Security's web security, this class configures the security rules
    for authentication and authorization
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // ownerService will be used to get the owner's details from the database
    private final OwnerService ownerService;

    public SecurityConfig(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /* @Bean means this method will register a Spring Bean so that Spring Security can load the owner's information
        for authentication
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return ownerService;
    }

    /* @Bean means this method registers a Spring Bean so that AuthenticationProvider from Spring security can
        use the OwnerService and a password encoder to authenticate an owner account
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {

        // Creates a new DauAuthenticationProvider object provider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // provider authenticates the user details with ownerService
        provider.setUserDetailsService(ownerService);

        // provider sets the passwordEncoder object
        provider.setPasswordEncoder(passwordEncoder());

        // return authentication
        return provider;

    }

    /* @Bean means this will register a spring Bean so that passwordEncoder can use BCryptPasswordEncoder()
        from Spring Security to securely encode and verify passwords
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* @Bean means this method registers a Spring Bean to configure the securityFilterChain for the application
        using Spring Security
       This method disables csrf protection, sets up the login page for authentication and redirects to the dashboard
       after successful login.
       The method also permits requests to static resources and defines which urls are public and which are private
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/dashboard", true)
                )
                .authorizeHttpRequests(auth -> auth
                        // Permit all requests to static resources (CSS, JS, images, etc.)
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // Permit your public pages
                        .requestMatchers("/", "/index", "/login", "/signup").permitAll()
                        // All other requests require authentication
                        .requestMatchers("/dashboard/**", "/pets/**", "/account-info").authenticated()
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                );

        return http.build();
    }

    @Bean
    public JavaMailSender javaMailSender(Environment env) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", env.getProperty("spring.mail.properties.mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        return mailSender;
    }
}
