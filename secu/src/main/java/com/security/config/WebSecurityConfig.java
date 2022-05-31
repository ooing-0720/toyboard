package com.security.config;

import com.security.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    private final UserService userService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/h2-console/**");
    }
/*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/login", "/signup", "/user").permitAll()
                    .antMatchers("/").hasRole("USER")
                    .antMatchers("/admin").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                .and()
                    .logout()
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true);

        return http.build();
    }
*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> {
                            try {
                                authz
                                        .antMatchers("/login", "/signup", "/user").permitAll()
                                        .antMatchers("/").hasRole("USER")
                                        .antMatchers("/admin").hasRole("ADMIN")
                                        .anyRequest().authenticated()
                                        .and()
                                        .formLogin()
                                        .loginPage("/login")
                                        .defaultSuccessUrl("/")
                                        .and()
                                        .logout()
                                        .logoutSuccessUrl("/login")
                                        .invalidateHttpSession(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    /*
    @Bean
    AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder, PasswordEncoder encoder) throws Exception {
        return builder.userDetailsService(userService).passwordEncoder(encoder).and().build();
    }
     */

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("email")
                .password("password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
