package com.app.quantitymeasurement.config;

import com.app.quantitymeasurement.auth.OAuth2SuccessHandler;
import com.app.quantitymeasurement.jwt.JwtAuthFilter;
import com.app.quantitymeasurement.jwt.JwtService;
import com.app.quantitymeasurement.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

        @Autowired(required = false)
                private ClientRegistrationRepository clientRegistrationRepository;

        @Bean
        @Autowired
        public OAuth2SuccessHandler oAuth2SuccessHandler(JwtService jwtService, UserService userService) {
                return new OAuth2SuccessHandler(jwtService, userService);
        }

        @Bean
        @Autowired
        public JwtAuthFilter jwtAuthFilter(JwtService jwtService, UserService userService) {
                return new JwtAuthFilter(jwtService, userService);
        }

        private static final String[] PUBLIC_PATHS = {
                        "/",
                        "/auth/**",
                        "/oauth2/**",
                        "/login/oauth2/code/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/api-docs/**",
                        "/v3/api-docs/**",
                        "/h2-console/**",
                        "/actuator/health",
                        "/actuator/info",
                        "/error"
        };

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http,
                        JwtAuthFilter jwtAuthFilter,
                        OAuth2SuccessHandler oAuth2SuccessHandler) throws Exception {
                http
                                // Enable CORS
                                .cors(cors -> {
                                })

                                // Disable CSRF (JWT based)
                                .csrf(AbstractHttpConfigurer::disable)

                                // Stateless session (important for JWT)
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                                // Authorization rules
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(PUBLIC_PATHS).permitAll()
                                                .requestMatchers("/api/v1/quantities/**").permitAll()
                                                .requestMatchers("/api/v1/users/**").permitAll()
                                                .anyRequest().authenticated())

                                // Allow H2 console iframe
                                .headers(headers -> headers
                                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))

                                // Add JWT filter
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                if (clientRegistrationRepository != null) {
                        http.oauth2Login(oauth2 -> oauth2
                                        .loginPage("/oauth2/authorization/google") // ✅ ADD THIS LINE
                                        .successHandler(oAuth2SuccessHandler)
                                        .redirectionEndpoint(redir -> redir.baseUri("/login/oauth2/code/*")));
                }

                return http.build();
        }
}
