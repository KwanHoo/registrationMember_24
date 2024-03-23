package com.moriah.registrationMember.member.service;

import com.moriah.registrationMember.member.dto.MemberDTO;
import com.moriah.registrationMember.member.entity.MemberEntity;
import com.moriah.registrationMember.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    //-- repository 의존성 주입
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        // 1. dto 객체 -> entity 객체로 변환
        // 2. repository의 save 매서드 호출

        // repository의 save메서드 호출 (조건. entity객체를 repository로 넘겨줘야 함)

        //1.
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        //2.
        memberRepository.save(memberEntity); //JPA가 제공해주는 save 메서드

    }

    public MemberDTO login(MemberDTO memberDTO) {
        /*
        * 1. 회원이 입력한 이메일로 DB에서 조회를 함
        * 2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        
        if (byMemberEmail.isPresent()){
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있음)
            MemberEntity memberEntity = byMemberEmail.get(); //optional로 감싸진 객체 포장지 벗겨내는 get
            // entity 객체를 가져올 수 있음
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                // 비밀번호 일치
                //- 엔티티를 DTO객체로 변환
                // 엔티티 객체는 서비스 안에서만
                // 컨트롤러에서는 DTO 객체 사용
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }else {
                // 비밀번호 불일치(로그인실패)
                return null;
            }
        }else {
            // 조회 결과가 없다 (해당 이메일을 가진 회원이 없다)
            return null;
        }

    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        //리스트에서 리스트로 for 문 사용
        //for each
        for (MemberEntity memberEntity: memberEntityList){
//            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//            memberDTOList.add(memberDTO);
            // 위 두줄 아래 한줄로 간단히 작성
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }
        return memberDTOList;
        // entity 객체 dto 객체로 변환
    }
}
