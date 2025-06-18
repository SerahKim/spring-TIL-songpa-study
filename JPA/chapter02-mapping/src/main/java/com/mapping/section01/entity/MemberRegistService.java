package com.mapping.section01.entity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberRegistService {

    private MemberRepository memberRepository;

    public MemberRegistService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    // 전달해주는 값은 Entity가 아닌 DTO (데이터 등록을 위해)
    // DTO -> Entity 가공
    public void registMember(MemberRegistDTO newMember) {

        // DTO -> Entity 가공
        Member member = new Member(
                newMember.getMemberId(),
                newMember.getMemberPwd(),
                newMember.getMemberName(),
                newMember.getPhone(),
                newMember.getAddress(),
                newMember.getEnrollDate(),
                newMember.getMemberRole(),
                newMember.getStatus()
        );

        // Entity 전달
        // .save == .commit : EntityManager의 persist 동작
        memberRepository.save(member);
    }
}
