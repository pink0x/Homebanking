package com.mindhub.homebaking.configurations;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.server.authentication.logout.HttpStatusReturningServerLogoutSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth ->

                auth.requestMatchers("/index.html","/assets/styles.css","/assets/index.js").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/clients").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/clients/current").hasAnyAuthority("CLIENT")
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                        .anyRequest().denyAll());

        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        http.headers(header -> header.frameOptions(options-> options.disable()));

        http.formLogin(formLogin->
                formLogin.loginPage("/index.html")
                        .usernameParameter("email")//primer parametro
                        .passwordParameter("password")//segundo parametro
                        .loginProcessingUrl("/api/login")//endpoint para peticion
                        .failureHandler((request, response, exception) -> response.sendError(403))
                        .successHandler((request, response, authentication) -> clearAuthenticationAttributes(request))
                        .permitAll());

        http.logout(logout ->
                logout.logoutUrl("/api/logout")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler((request, response, authentication) -> new HttpStatusReturningServerLogoutSuccessHandler()));

        http.exceptionHandling( httpSecurityExceptionHandlingConfigurer ->
                                 httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(
                                         (request, response, authException) -> response.sendError(401)));

        http.rememberMe(Customizer.withDefaults());



        return http.build();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }
}
