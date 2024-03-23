package com.moriah.registrationMember.member.controller;

import com.moriah.registrationMember.member.dto.MemberDTO;
import com.moriah.registrationMember.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //-- 생성자 주입 : 객체
    // 이 필드를 매개변수로 하는 생성자를 @RequiredArgsConstructor가 주입을 받게 해줌
    // 주입 : controller 클래스가 service 클래스의 자원(매서드, 필드) 사용 권한이 생김
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save"; // "save" 라는 이름의 파일을 templates 에서 찾음
    }

//    @PostMapping("/member/save")
//    public String save(@RequestParam("memberEmail") String memberEmail,
//                       @RequestParam("memberPassword") String memberPassword,
//                       @RequestParam("memberName") String memberName) {
//
//        System.out.println("MemberController.save"); //soutm
//        System.out.println("memberEmail = " + memberEmail + ", memberPassword = " + memberPassword + ", memberName = " + memberName); //soutp
//        //memberEmail = aaa, memberPassword = aaaa, memberName = aaa1
//        return "index";
//    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {

        System.out.println("MemberController.save"); //soutm
        System.out.println("memberDTO = " + memberDTO);
        //memberDTO = MemberDTO(id=null, memberEmail=aaa2, memberPassword=aaaa, memberName=aaaa2)

        //-- 안씀 (위 @RequiredArgsConstructor 로 주입해서 사용함)
//        MemberService memberService = new MemberService();
        memberService.save(memberDTO);

        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {

        System.out.println("MemberController.login");
        System.out.println("memberDTO = " + memberDTO);

        MemberDTO loginResult = memberService.login(memberDTO);

        if (loginResult != null){
            //login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail()); // 세션정보에 담아줌
            return "main";
        }else {
            //login 실패
            return "login";
        }

    }
}
