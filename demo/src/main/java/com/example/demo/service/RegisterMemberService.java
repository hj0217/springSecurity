package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//회원가입 로직
//두번째
@Service
@RequiredArgsConstructor
public class RegisterMemberService {
    private final PasswordEncoder passwordEncoder; //passwordEncoder를 생성자타입으로 주입하므로서 추후에 인코더 변경되어도 코드를 수정할 필요가 없음??
    private final MemberRepository repository;

//    @Autowired
//    public RegisterMemberService(PasswordEncoder passwordEncoder, MemberRepository repository) {
//        this.passwordEncoder = passwordEncoder;
//        this.repository = repository;
//    }

    public Long join(String userid, String pw) {
        Member member = Member.createUser(userid, pw, passwordEncoder); //Member 객체에 있는 createUser 매소드 들려서 패스워드 encoding후 member 객체에 넣어짐
        validateDuplicateMember(member); // 중복체크 로직 (코드 재활용) // 중복회원 존재하면 바로 예외 던짐
        repository.save(member); // 무사히 통과되면 JPA save()로 DB에 회원 정보 저장

        return member.getId(); // 저장 후 자동생성된 회원id 가져오기
    }



    private void validateDuplicateMember(Member member) {
        repository.findByUserid(member.getUserid())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}