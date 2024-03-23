package com.moriah.registrationMember.member.service;

import com.moriah.registrationMember.member.dto.MemberDTO;
import com.moriah.registrationMember.member.entity.MemberEntity;
import com.moriah.registrationMember.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    //-- repository 의존성 주입
    private final MemberRepository memberRepositoryt;
    public void save(MemberDTO memberDTO) {
        // 1. dto 객체 -> entity 객체로 변환
        // 2. repository의 save 매서드 호출

        // repository의 save메서드 호출 (조건. entity객체를 repository로 넘겨줘야 함)

        //1.
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        //2.
        memberRepositoryt.save(memberEntity); //JPA가 제공해주는 save 메서드

    }
}
