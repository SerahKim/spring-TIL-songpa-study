package com.springdatajpa.menu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tbl_menu")
/*
@Setter 지양
-> 객체를 언제든지 변경할 수 있으면 객체의 안정성이 보장되지 않기 때문이다.
-> 값 변경이 필요한 경우에는 해당 기능을 위한 메소드를 별도로 생성한다.
*/
@Getter
// 기본 생성자 생성 어노테이션
@NoArgsConstructor(access = AccessLevel.PROTECTED) // PROTECTED : 해당 클래스를 상속한 클래스에서만 사용 가능
//@AllArgsConstructor : 모든 필드에 대한 생성자 지양
//@ToString : 사용 가능하나 연관 관계 매핑 필드는 제거하고 사용
/*
엔티티는 DTO와 다르게 DB와 직접 연결되어 있는 객체이다.
따라서, 값이 쉽게 바뀌게되면 DB 안정성이 보장될 수 없기 때문에 진짜 필요한 최소한의 어노테이션으로 작성하여 만들어야 한다.
*/
public class Menu {

    @Id
    private int menuCode;
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;

}
