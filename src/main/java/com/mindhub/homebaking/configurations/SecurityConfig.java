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

                auth.requestMatchers("/assets/pages/transactions.html","/api/clients/current","/assets/pages/transactions.js","/index.html","/assets/**","/assets/tailwind.config.js","/api/accounts","/assets/pages/cards.html", "/assets/pages/account.html","/api/loans").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/clients","/api/login","/api/clients/current/cards").permitAll()

                        //AUTORIZACION ADMIN
                        .requestMatchers(HttpMethod.GET,"/admin/**","/api/loans","api/loan/create","/assets/pages/accounts.html","api/clients/current").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST,"api/loans/create","/api/loans").hasAnyAuthority("ADMIN")

                        //AUTORIZACION CLIENT

                        .requestMatchers(HttpMethod.GET, "/api/loans","/api/clients/current/transactions","/assets/pages/transactions.html","/assets/pages/transactions.js","/api/clients/current","/assets/pages/cards.html", "/assets/pages/account.html").hasAnyAuthority("CLIENT")
                        .requestMatchers(HttpMethod.POST,  "/api/loans","/api/transactions","/api/account","/api/clients/current/cards","/api/accounts/create").hasAnyAuthority("CLIENT")
                        .requestMatchers(HttpMethod.PATCH,"/api/clients/current/cards","/api/accounts","/api/loans").hasAnyAuthority("CLIENT")
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
