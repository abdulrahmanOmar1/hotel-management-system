package org.example.finalprojectweb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.example.finalprojectweb.entity.User.Role.ADMIN;
import static org.example.finalprojectweb.entity.User.Role.CUSTOMER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/v1/auth/**").permitAll()
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    //rooms
                    .requestMatchers(HttpMethod.GET,"/rooms").hasAnyAuthority(ADMIN.name() , CUSTOMER.name())
                    .requestMatchers(HttpMethod.GET,"/rooms/{id}").hasAnyAuthority(ADMIN.name() , CUSTOMER.name())
                    .requestMatchers(HttpMethod.POST,"/rooms").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(HttpMethod.PUT,"/rooms/{id}").hasAnyAuthority(ADMIN.name() )
                    .requestMatchers(HttpMethod.DELETE,"/rooms/{id}").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(HttpMethod.GET,"/rooms/available").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(HttpMethod.GET,"/rooms/reserved").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(HttpMethod.GET,"/rooms/reserved/info").hasAnyAuthority(CUSTOMER.name())
                    .requestMatchers(HttpMethod.GET,"/rooms/available/info").hasAnyAuthority(CUSTOMER.name())
                    //housekeepingtasks
                    .requestMatchers(HttpMethod.GET,"/housekeepingtasks").hasAnyAuthority(ADMIN.name() )
                    .requestMatchers(HttpMethod.GET,"/housekeepingtasks/{id}").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(HttpMethod.POST,"/housekeepingtasks").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(HttpMethod.PUT,"/housekeepingtasks/{id}").hasAnyAuthority(ADMIN.name() )
                    .requestMatchers(HttpMethod.DELETE,"/housekeepingtasks/{id}").hasAnyAuthority(ADMIN.name())
                    //reservations
                    .requestMatchers(HttpMethod.GET,"/reservations").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(HttpMethod.GET,"/reservations/{id}").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(HttpMethod.POST,"/reservations").hasAnyAuthority(ADMIN.name() ,  CUSTOMER.name())
                    .requestMatchers(HttpMethod.PUT,"/reservations/{id}").hasAnyAuthority(ADMIN.name() , CUSTOMER.name() )
                    .requestMatchers(HttpMethod.DELETE,"/reservations/{id}").hasAnyAuthority(ADMIN.name() , CUSTOMER.name())
                    .requestMatchers(HttpMethod.GET,"/reservations/searchByName/{customerName}").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(HttpMethod.GET,"/reservations/searchById/{customerId}").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(HttpMethod.GET,"/reservations/searchByDate/{date}").hasAnyAuthority(ADMIN.name() , CUSTOMER.name())
                    .requestMatchers(HttpMethod.GET,"/reservations/approve/{id}").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(HttpMethod.GET,"/reservations/reject/{id}").hasAnyAuthority(ADMIN.name())
                    //users
                    .requestMatchers(HttpMethod.GET,"/users").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(HttpMethod.GET,"/users/login").hasAnyAuthority(ADMIN.name() , CUSTOMER.name())
                    .requestMatchers(HttpMethod.GET,"/users/{id}").hasAnyAuthority(ADMIN.name() , CUSTOMER.name())
                    .requestMatchers(HttpMethod.POST,"/users").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(HttpMethod.PUT,"/users/{id}").hasAnyAuthority(ADMIN.name() )
                    .requestMatchers(HttpMethod.DELETE,"/users/{id}").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(HttpMethod.POST,"/users/signup").hasAnyAuthority(ADMIN.name() , CUSTOMER.name())
                    .requestMatchers(HttpMethod.PUT,"/users/{id}/changePassword").hasAnyAuthority(ADMIN.name() ,CUSTOMER.name() )

                    .anyRequest().authenticated())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
  }
}
