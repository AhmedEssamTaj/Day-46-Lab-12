package com.example.day46blogsysterm.Config;

import com.example.day46blogsysterm.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurity {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
   public DaoAuthenticationProvider authenticationProvider() {
       DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
       provider.setUserDetailsService(myUserDetailsService);
       provider.setPasswordEncoder(new BCryptPasswordEncoder());
       return provider;
   }

   @Bean
   public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

                .and()

                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/user/register").permitAll()
                .requestMatchers("/api/v1/user/update").hasAuthority("USER")
                .requestMatchers("/api/v1/user/get-all").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/blog/create","/api/v1/blog/update/{blogId}",
                        "/api/v1/blog/delete/{blogId}" , "/api/v1/blog/get-my-blogs").hasAuthority("USER")
                .requestMatchers("/api/v1/blog/get-all","/api/v1/blog//get-by-title/{title}").permitAll()
                .requestMatchers("/api/v1/blog/get-blog/{blogId}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/blog/delete/{blogId}").hasAuthority("ADMIN")
                .anyRequest().authenticated()

                .and()

                .logout().logoutUrl("/api/v1/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)

                .and()

                .httpBasic();
        return http.build();


   }

}
