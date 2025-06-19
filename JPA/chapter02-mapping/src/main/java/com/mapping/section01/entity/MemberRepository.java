package com.mapping.section01.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository // DB에 접근하는 역할은 하는 객체 달아주는 어노테이션
public class MemberRepository {

    @PersistenceContext // EntityManage를 사용하겠다는 어노테이션
    private EntityManager entityManager;

    public void save(Member member) {
         entityManager.persist(member);
    }

    public String findNameById(String memberId) {
        // 테이블 이름이 아닌 Entity 이름이 들어간다.
        // 별칭을 붙여야 한다.
        String jpql = "SELECT m.memberName FROM entityMember m WHERE m.memberId = '" + memberId + "'";

        return entityManager.createQuery(jpql, String.class).getSingleResult();
    }
}
