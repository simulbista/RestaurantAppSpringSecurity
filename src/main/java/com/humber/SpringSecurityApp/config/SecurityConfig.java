package com.humber.SpringSecurityApp.config;

import com.humber.SpringSecurityApp.services.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MyUserDetailService myUserDetailService;

    public SecurityConfig(MyUserDetailService myUserDetailService){
        this.myUserDetailService = myUserDetailService;
    }

    //filter chain rules
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/restaurant/home/**","/register/**").permitAll()
                        .requestMatchers("/restaurant/menu/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/restaurant/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(httpSecurityFormLoginConfigurer -> {
                   httpSecurityFormLoginConfigurer.loginPage("/login").permitAll();
                })
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/restaurant/menu/").permitAll());

//                .formLogin((Customizer.withDefaults()));
        return http.build();
    }


    // creating users of the app - in memory
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails admin1 = User.withUsername("owner")
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user1 = User.withUsername("guest")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin1, user1);
//    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        return myUserDetailService;
//    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    //ignoring static resources and h2-console from the security filter rules
    @Bean
    public WebSecurityCustomizer ignoreResources(){
        return (webSecurity) -> webSecurity.ignoring().requestMatchers("/css/**", "/h2-console/**");
    }

    //method to encrypt the user passwords
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
