//package PillSafe.PillSafeweb.controller;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .antMatchers("/", "/login**").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .oauth2Login(oauth2Login ->
//                        oauth2Login
//                                .defaultSuccessURL("/loginSuccess") // 로그인 성공 시 이동할 URL
//                );
//    }
//}
