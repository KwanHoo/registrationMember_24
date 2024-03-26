package com.moriah.registrationMember.ajaxEx.controller;

import com.moriah.registrationMember.ajaxEx.controller.dto.AjaxDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AjaxController {
    @GetMapping("/ex01")
    public String getAjaxex01() {
        System.out.println("AjaxController.ex01");
        return "index";     //index.html에 작성된 내용이 출력됨
    }

    @PostMapping("/ex02")
    public @ResponseBody String ex02() {
        System.out.println("AjaxController.ex02");
        return "리턴값이 그대로 실려서 넘어간다";     //@ResponseBody 하면 리턴값이 그대로 출력됨
    }

    @GetMapping("/ex03")
    public @ResponseBody String ex03(@RequestParam("param1") String param1, @RequestParam("param2") String param2){
        System.out.println("param1 = " + param1 + ", param2 = " + param2);

        return "ex03 메서드 호출 완료";
    }

    @PostMapping("/ex04")
    public @ResponseBody String ex04(@RequestParam("param1") String param1, @RequestParam("param2") String param2){
        System.out.println("param1 = " + param1 + ", param2 = " + param2);

        return "ex04 메서드 호출 완료";
    }

    @GetMapping("/ex05")
    public @ResponseBody AjaxDto ex05(@ModelAttribute AjaxDto ajaxDto){
        System.out.println("ajaxDto = " + ajaxDto);

        return ajaxDto;
    }

    @PostMapping("/ex06")
    public @ResponseBody AjaxDto ex06(@ModelAttribute AjaxDto ajaxDto){
        System.out.println("ajaxDto = " + ajaxDto);

        return ajaxDto;
    }

    // @RequestBody 이용해서 JSON타입으로 넘겨받기
    @PostMapping("/ex07")
    public @ResponseBody AjaxDto ex07(@RequestBody AjaxDto ajaxDto){
        System.out.println("ajaxDto = " + ajaxDto);

        return ajaxDto;
    }

    private List<AjaxDto> DTOList() {
        List<AjaxDto> dtoList =  new ArrayList<>();
        dtoList.add(new AjaxDto("data1", "data11"));
        dtoList.add(new AjaxDto("data2", "data22"));
        dtoList.add(new AjaxDto("data3", "data33"));
        dtoList.add(new AjaxDto("data4", "data44"));
        return dtoList;
    }
    @PostMapping("/ex08")
    public @ResponseBody List<AjaxDto> ex08(@RequestBody AjaxDto ajaxDto){
        System.out.println("ajaxDto = " + ajaxDto);
        List<AjaxDto> dtoList = DTOList();
        dtoList.add(ajaxDto);

        return dtoList;
    }
}
