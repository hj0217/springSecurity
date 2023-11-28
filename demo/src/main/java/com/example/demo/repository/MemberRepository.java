package com.example.demo.repository;
//Spring Data JPA

import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//역할 : DB에서 회원정보를 받아와서 스프링부트에게 넘겨주기 위한
//세번째 step
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserid(String userId); //DB에서 userId로 결과 조회

}