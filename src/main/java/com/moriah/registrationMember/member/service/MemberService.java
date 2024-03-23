package com.moriah.registrationMember.member.service;

import com.moriah.registrationMember.member.dto.MemberDTO;
import com.moriah.registrationMember.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    //-- repository 의존성 주입
    private final MemberRepository memberRepositoryt;
    public void save(MemberDTO memberDTO) {

    }
}
