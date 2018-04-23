package com.codecool.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandler accessDeniedHandler;

    // @Autowired
    public WebSecurityConfig(AccessDeniedHandler accessDeniedHandler) {

        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER")
                .antMatchers("/", "/home", "/about").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin().loginPage("/login").permitAll()
                // .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
            .and()
                .logout().logoutSuccessUrl("/")
            .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser("user")
                    .password(encoder.encode("password"))
                    .roles("USER")
                .and()
                .withUser("admin")
                    .password(encoder.encode("password"))
                    .roles("ADMIN")
                .and()
                .withUser("superuser")
                    .password(encoder.encode("password"))
                    .roles("USER", "ADMIN");
    }
}