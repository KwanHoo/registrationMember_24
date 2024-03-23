package com.moriah.registrationMember.member.entity;

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

}
