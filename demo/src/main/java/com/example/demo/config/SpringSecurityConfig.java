package com.example.demo.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //thymeleaf의 csrf토큰 기능을 지원함! 토큰없으면 403에러를 뱉어냄.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            //http.csrf().disable().cors().disable()
                http.csrf(AbstractHttpConfigurer::disable);
                http.cors(AbstractHttpConfigurer::disable);
                http
                    .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() // 스.부3부터 적용된 스.시6부터  forward방식 페이지 이동에도 인증이 걸리도록 변경됨.JSP나 타임리프 등 컨트롤러에서 화면 파일명을 리턴해 view resolver가 작동하는 페이지이동의 경우 dispatcherTypeMatchers 설정이 필요함.
                        .requestMatchers("/status", "/images/**", "/view/join", "/auth/join").permitAll()
                )
                .formLogin(login -> login // form 방식 로그인 사용 <-> httpbasics??
                        .loginPage("/view/login") // login요청 시 login 페이지로 이동
                        .loginProcessingUrl("/login-process") //login.html id/pw form 제출 액션
                        .usernameParameter("userid")
                        .passwordParameter("pw")
                        .defaultSuccessUrl("/view/dashboard", true) //로그인 성공 시 이동페이지
                        .permitAll() // 대시보드 이동이 막히면 안되므로 얘는 허용
                        )
                        .logout(withDefaults());	// 로그아웃은 기본설정으로 (/logout으로 인증해제)

        return http.build();
    }
}
