package com.example.demo.config;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


//역할 : DB에서 회원정보를 받아와서 스프링부트에게 넘겨주기 위한 => UserDetailsService를 구현하게 되면 콘솔창에 Spring의 임시 비밀번호가 안뜨게 됨!
//첫번째 step
@Component
public class MyUserDetailService implements UserDetailsService {

    private final MemberService memberService; // JPA인터페이스 MemberRepository의 findByUserid(userId)로 결과 받아와 Member 객체에 담음

    @Autowired
    public MyUserDetailService (MemberService memberService) {
        this.memberService = memberService;
    }

    @Override //// Spring Security가 인증 확인을 위해 바라보는 객체가 UserDetailService
    public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException {
        Optional<Member> findOne = memberService.findOne(insertedUserId);
        Member member = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다 ㅠ"));

        return User.builder() //// DB 검색 결과를 User에 담음 (User는 UserDetails와 CredentialsContainer 구현한 구현체로 Spring Security 프로세스에 필요한 객체
                .username(member.getUserid())
                .password(member.getPw())
                .roles(member.getRoles())
                .build();
    }
}
