package br.com.projlib.bookshelf.infra.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class AuthenticationConfiguration {

    private final AuthenticationFilter authenticationFilter;
    private final AppAuthenticationEntryPoint appAuthenticationEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/v1/authentication").anonymous()
                .requestMatchers(HttpMethod.POST,"/api/v1/authentication").anonymous()
                .requestMatchers("/api/v1/health").anonymous()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(this.appAuthenticationEntryPoint)
                .and()
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic();

        httpSecurity.addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
//    private final UserAccountService userAccountService;
//    private final AppAuthenticationEntryPoint appAuthenticationEntryPoint;
//    private final AuthenticationFilter authenticationFilter;
//
//    @Autowired
//    public AuthenticationConfiguration(UserAccountService userAccountService,
//                                       AppAuthenticationEntryPoint appAuthenticationEntryPoint,
//                                       AuthenticationFilter authenticationFilter) {
//        super();
//        this.userAccountService = userAccountService;
//        this.appAuthenticationEntryPoint = appAuthenticationEntryPoint;
//        this.authenticationFilter = authenticationFilter;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.userAccountService)
//                .passwordEncoder(this.passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/authentication").anonymous()
//                .antMatchers(HttpMethod.POST,"/api/v1/authentication").anonymous()
//                .antMatchers("/api/v1/health").anonymous()
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(this.appAuthenticationEntryPoint)
//                .and()
//                .cors()
//                .and()
//                .csrf().disable()
//                .formLogin().disable()
//                .httpBasic();
//
//        httpSecurity.addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    }

}

