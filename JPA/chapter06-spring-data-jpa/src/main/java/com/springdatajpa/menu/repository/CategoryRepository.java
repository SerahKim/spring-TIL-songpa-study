package com.springdatajpa.menu.repository;

import com.springdatajpa.menu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /* JPQL */
//    @Query(value = "SELECT c FROM Category c ORDER BY c.categoryCode")
    /* Native Query */
    // nativeQuery의 디폴트값은 false이다.
    @Query(
            value = "SELECT category_code, category_name, ref_category_code FROM tbl_category ORDER BY category_code",
            nativeQuery = true
    )
    List<Category> findAllCategory();
}
