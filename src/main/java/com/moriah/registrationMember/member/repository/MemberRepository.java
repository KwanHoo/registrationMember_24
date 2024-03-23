package com.moriah.registrationMember.member.repository;

import com.moriah.registrationMember.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional; //null방지 해주는

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // 이메일로 회원 정보 조회
    //Select * From member_table where member_email =?
    Optional<MemberEntity> findByMemberEmail(String memberEmail);

}
