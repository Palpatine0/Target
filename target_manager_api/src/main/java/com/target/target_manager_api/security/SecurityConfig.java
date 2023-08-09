
package com.target.target_manager_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //sheet login
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/admin/login")
                .successHandler(new MyLoginSuccessHandler())
                .failureHandler(new MyLoginFailureHandler());

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin/login").permitAll()
                .anyRequest().authenticated();

        http.logout()
                .logoutUrl("/admin/logout")
                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                .clearAuthentication(true)
                .invalidateHttpSession(true);

        http.exceptionHandling()
                .authenticationEntryPoint(new MyEntryPointHandler())
                .accessDeniedHandler(new MyAccessDeniedHandler());

        http.csrf().disable();
        http.cors();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

