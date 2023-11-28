package com.example.demo.controller;

import com.example.demo.dto.MemberJoinDto;
import com.example.demo.service.RegisterMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//회원가입 로직
//첫번째
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final RegisterMemberService registerMemberService;

//    public AuthorizationController(RegisterMemberService registerMemberService) {
//        this.registerMemberService = registerMemberService;
//    }

    //가입로직
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinDto dto) { //가입정보 임시로 담는 container 역할 dto
//    public ResponseEntity<String> join(@RequestParam(value = "userid") String userid,
//                                       @RequestParam(value = "pw") String pw) { //가입정보 임시로 담는 container 역할 dto
System.out.println(dto.toString());
//System.out.println("test"+userid);
//System.out.println("test"+ pw);
        try {
            registerMemberService.join(dto.getUserid(), dto.getPw());
            //registerMemberService.join(userid, pw);
            return ResponseEntity.ok("join success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
