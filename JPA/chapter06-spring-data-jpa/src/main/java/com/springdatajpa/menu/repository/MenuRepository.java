package com.springdatajpa.menu.repository;

import com.springdatajpa.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/* JpaRepository<엔티티, Id 타입> */
public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
