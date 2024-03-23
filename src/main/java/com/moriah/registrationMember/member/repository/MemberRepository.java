package com.moriah.registrationMember.member.repository;

import com.moriah.registrationMember.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
