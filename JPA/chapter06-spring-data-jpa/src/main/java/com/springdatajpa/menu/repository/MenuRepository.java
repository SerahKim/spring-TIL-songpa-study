package com.springdatajpa.menu.repository;

import com.springdatajpa.menu.entity.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
JpaRepository<엔티티, Id 타입>
JpaRepository를 확장하여 만들면 자동으로 빈으로 등록된다.
*/
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    // WHERE menu_price = ? 와 같은 의미
    List<Menu> findByMenuPrice(Integer menuPrice);

    // 파라미터로 전달받은 가격을 초과하는 메뉴 목록 조회
    List<Menu> findByMenuPriceGreaterThan(Integer menuPrice);

    // 파라미터로 전달받은 가격을 초과하는 메뉴 목록 가격순으로 조회
    List<Menu> findByMenuPriceGreaterThanOrderByMenuPrice(Integer menuPrice);

    List<Menu> findByMenuPriceGreaterThan(Integer menuPrice, Sort sort);
}
