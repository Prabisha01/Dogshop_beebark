package com.example.dogshop.Security;


import com.example.dogshop.Service.Impl.CustomerUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class Spring_security_config {
    private final CustomerUserDetailService customerUserDetailService;


    public Spring_security_config(CustomerUserDetailService customerUserDetailService) {
        this.customerUserDetailService = customerUserDetailService;
    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customerUserDetailService);
        authenticationProvider.setPasswordEncoder(PasswordEncoderUtil.getInstance());
        return authenticationProvider;
    }



    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws  Exception{
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/**", "/homepage/**","/admin/**", "/blog/**")
                .permitAll()
                .requestMatchers("/admin/**")
                .hasAuthority("Admin")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user/homepage",true)
                .usernameParameter("email")
                .permitAll()
                .and()
                .httpBasic();
        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers("/css/**","/images/**","/javascript/**");
    }
}