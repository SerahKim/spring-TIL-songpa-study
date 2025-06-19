package com.mapping.section01.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "entityMember")
@Table(name = "tbl_member")
// AccessType. FIELD : 디폴트 값, 클래스 레벨에 설정 가능, 모든 필드를 대상으로 적용하겠다는 의미
@Access(AccessType.FIELD)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private int memberNo;

    // AccessType. PROPERTY : 필드에 직접 접근하지 않고 getter 메소드를 이용해 memberId에 접근
    @Access(AccessType.FIELD)
    @Column(name = "member_id", unique = true, nullable = false, columnDefinition = "varchar(10)")
    private String memberId;

    @Column(name = "member_pwd", nullable = false)
    private String memberPwd;

    @Access(AccessType.PROPERTY)
    @Column(name = "member_name")
    private String memberName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address", length = 900)
    private String address;

    @Column(name = "enroll_date")
    private LocalDateTime enrollDate;

    @Column(name = "member_role")
//    @Enumerated(EnumType.STRING) // 우리가 enum에서 설정해준 문자열 그대로 들어가게 됨
    private MemberRole memberRole;

    @Column(name = "status", columnDefinition = "char(1) default 'Y'")
    private String status;

    protected Member() {
    }

    public Member(String memberId, String memberPwd, String memberName, String phone, String address, LocalDateTime enrollDate, MemberRole memberRole, String status) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.phone = phone;
        this.address = address;
        this.enrollDate = enrollDate;
        this.memberRole = memberRole;
        this.status = status;
    }

    @Access(AccessType.PROPERTY)
    public String getMemberName() {
        System.out.println("getMemberName 메소드를 통한 Access 확인");
        return memberName + "님";
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
