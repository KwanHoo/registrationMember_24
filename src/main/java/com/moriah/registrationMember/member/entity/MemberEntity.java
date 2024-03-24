package com.moriah.registrationMember.member.entity;

import com.moriah.registrationMember.member.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {

    @Id //pk지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 지정
    private Long id;

    @Column(unique = true) //unique 제약조건 추가
    private String memberEmail;

    private String memberPassword;

    private String memberName;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        // static 매서드로 정의
        MemberEntity memeberEntity = new MemberEntity();
        memeberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memeberEntity.setMemberName(memberDTO.getMemberName());
        memeberEntity.setMemberPassword(memberDTO.getMemberPassword());
        return memeberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO){
        // static 매서드로 정의
        MemberEntity memeberEntity = new MemberEntity();
        memeberEntity.setId(memberDTO.getId());
        memeberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memeberEntity.setMemberName(memberDTO.getMemberName());
        memeberEntity.setMemberPassword(memberDTO.getMemberPassword());
        return memeberEntity;
    }
}
