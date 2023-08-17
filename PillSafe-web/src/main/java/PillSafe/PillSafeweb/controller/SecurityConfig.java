//package PillSafe.PillSafeweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
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


//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig {
//    private final OAuth2MemberService oAuth2MemberService;
//
//    public SecurityConfig() {
//        oAuth2MemberService = null;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        return httpSecurity.httpBasic().disable().csrf().disable().cors().and()
//                .authorizeRequests()
//                .requestMatchers("/private/**").authenticated() //private로 시작하는 uri는 로그인 필수
//                .anyRequest().permitAll() //나머지 uri는 모든 접근 허용
//                .and().oauth2Login()
//                .loginPage("/loginForm") //로그인이 필요한데 로그인을 하지 않았다면 이동할 uri 설정
//                .defaultSuccessUrl("/").userInfoEndpoint()//로그인 완료 후 회원 정보 받기
//                .userService(oAuth2MemberService).and().and().build(); //로그인 후 받아온 유저 정보 처리
//    }
//}