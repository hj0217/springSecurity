package com.example.demo.dto;

import lombok.Getter;
import lombok.ToString;
@Getter
@ToString
public class MemberJoinDto {

    private String userid;
    private String pw;

    public void setUserid(String userid) {
System.out.println("조인할 회원 정보 임시 저장공간: MemberJoinDto");
        this.userid = userid;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

}
