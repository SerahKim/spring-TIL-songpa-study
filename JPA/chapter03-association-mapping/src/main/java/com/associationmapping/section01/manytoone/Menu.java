package com.associationmapping.section01.manytoone;

import jakarta.persistence.*;
import com.associationmapping.section01.manytoone.Category;

@Entity(name = "menu_and_category")
@Table(name = "tbl_menu")
public class Menu {

    // 컬럼명 자동맵핑
    @Id
    private int menuCode;
    private String menuName;
    private int menuPrice;
    /*
     영속성 전이
     ascadeType.PERSIST : Menu를 영속화할 때 category도 영속화하기 위한 옵션
     */
     /*
     기본적으로는 즉시 로딩되지만 필요에 따라 지연 로딩으로 변결할 수 있다.

     fetch 설정을 안했다면 @ManyToOne일 때의 default 값은 Eager다.
     즉시로딩(EAGER)을 하게 되면 조인하고 있는 카테고리의 내용도 한번에 가져오고
     지연로딩(LAZY)를 하게 되면 필요할 때만 카테고리의 내용을 가져온다.
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryCode")
    private Category category;
    private String orderableStatus;

    public Menu() {
    }

    public Menu(int menuCode, String menuName, int menuPrice, Category category, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderalbeStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", category=" + category +
                ", orderalbeStatus='" + orderableStatus + '\'' +
                '}';
    }
}
