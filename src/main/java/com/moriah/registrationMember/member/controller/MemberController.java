package com.moriah.registrationMember.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save"; // "save" 라는 이름의 파일을 templates 에서 찾음
    }

}
