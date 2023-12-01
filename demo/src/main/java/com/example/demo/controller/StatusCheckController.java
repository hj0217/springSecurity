package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusCheckController {

    @GetMapping
    public ResponseEntity<String> serverStatusCheck() {
        return ResponseEntity.ok("ok");
    }
}

//import org.springframework.security.core.GrantedAuthority;
//        import org.springframework.security.core.authority.SimpleGrantedAuthority;
//        import org.springframework.security.core.context.SecurityContextHolder;
//
//        import java.util.Collection;
//
//// ...
//
//public class YourService {
//
//    @PostAuthorize("hasRole('ROLE_USER')")
//    public void someMethod(String updatedField) {
//        // 업데이트된 필드 값을 이용하여 DB에서 새로운 역할 정보 가져오기
//        String newRole = yourService.getNewRole(updatedField);
//
//        // 현재 사용자의 역할 업데이트
//        updateRole(newRole);
//    }
//
//    private void updateRole(String newRole) {
//        // 현재 인증된 사용자의 역할 가져오기
//        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//
//        // 업데이트된 역할 추가
//        authorities.add(new SimpleGrantedAuthority(newRole));
//
//        // 새로운 권한을 사용하여 새로운 Authentication 객체 생성
//        Authentication updatedAuthentication = new UsernamePasswordAuthenticationToken(
//                SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
//                SecurityContextHolder.getContext().getAuthentication().getCredentials(),
//                authorities
//        );
//
//        // SecurityContextHolder에 새로운 Authentication 객체 설정
//        SecurityContextHolder.getContext().setAuthentication(updatedAuthentication);
//    }
//}