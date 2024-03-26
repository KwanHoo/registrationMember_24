package com.moriah.registrationMember.member.controller;

import com.moriah.registrationMember.member.dto.MemberDTO;
import com.moriah.registrationMember.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/member/")
    public String findAll(Model model){
        //Model -> 스프링에서 실어 나르는 역할(리스트를)
        List<MemberDTO> memberDTOList = memberService.findAll();
        //어떠한 html로 가져갈 데이터가 있다면 model 사용 (기본적인 사용방식, 이외에 다양한 방식 있음)
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    @GetMapping("/member/{id}")
    //    public String findById(@PathVariable("id") Long id, Model model) {
    public String findById(@PathVariable Long id, Model model){
        System.out.println("MemberController.findById");
        System.out.println("id = " + id + ", model = " + model);

        MemberDTO memberDTO = memberService.findById(id); //한명이니깐 DTO 타입으로 받음 위는 여러명이여서  List타입으로 받고
        model.addAttribute("member", memberDTO);
        System.out.println("memberDTO = " + memberDTO);
        return "detail";
    }


    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model){
        String myEmail = (String) session.getAttribute("loginEmail"); // object 타입을 string으로 형변환
        MemberDTO memberDTO = memberService.updateFrom(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "redirect:/member/" + memberDTO.getId(); //리다이렉트해줘서 상세페이지 띄워줌
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);

        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        session.invalidate();

        return "index";
    }

    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail){
        System.out.println("memberEmail = " + memberEmail);
        return "체크완료";
    }

}
