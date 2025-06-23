package com.nativequery.section01.simple;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NativeQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Menu nativeQueryByResultType(int menuCode) {
        /*
        Native Query 수행 결과를 엔티티에 매핑 시키려면 모든 컬럼이 조회 되어야만 매핑 가능하다.
        */
        String query = "SELECT menu_code, menu_name, menu_price, category_code, orderable_status " +
                "FROM tbl_menu " +
                "WHERE menu_code = ?";

        Query nativeQuery = entityManager.createNativeQuery(query, Menu.class)
                .setParameter(1, menuCode);

        // 기본 제공 타입은 Object이기 때문에 엔티티를 사용하고 싶다면 다운 캐스팅을 해줘야 한다.
        return (Menu) nativeQuery.getSingleResult();
    }

    public List<Object[]> nativeQueryByNoResultType() {
        String query = "SELECT menu_name, menu_price " +
                "FROM tbl_menu ";

        Query nativeQuery = entityManager.createNativeQuery(query);

        return nativeQuery.getResultList();
    }

    public List<Object[]> nativeQueryByAutoMapping() {
        // menu_count는 내가 만들어준 값이기 때문에 매핑 불가능
        // 따라서 Category 엔티티에 @SqlResultSetMappings 어노테이션의 value값을 설정해준다.
        String query
                = "SELECT a.category_code, a.category_name, a.ref_category_code," +
                " COALESCE(v.menu_count, 0) menu_count" +
                " FROM tbl_category a" +
                " LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code" +
                " FROM tbl_menu b" +
                " GROUP BY b.category_code) v ON (a.category_code = v.category_code)" +
                " ORDER BY 1";
        Query nativeQuery
                = entityManager.createNativeQuery(query, "categoryCountAutoMapping");
        return nativeQuery.getResultList();
    }

    public List<Object[]> nativeQueryByManualMapping() {
        String query
                = "SELECT a.category_code, a.category_name, a.ref_category_code," +
                " COALESCE(v.menu_count, 0) menu_count" +
                " FROM tbl_category a" +
                " LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code" +
                " FROM tbl_menu b" +
                " GROUP BY b.category_code) v ON (a.category_code = v.category_code)" +
                " ORDER BY 1";

        Query nativeQuery
                = entityManager.createNativeQuery(query, "categoryCountManualMapping");

        return nativeQuery.getResultList();
    }
}
