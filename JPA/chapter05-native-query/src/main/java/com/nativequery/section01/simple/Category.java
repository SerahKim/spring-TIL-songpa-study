package com.nativequery.section01.simple;

import jakarta.persistence.*;

@SqlResultSetMappings(
        value = {
                /* 자동 엔티티 매핑 */
                @SqlResultSetMapping(
                        name = "categoryCountAutoMapping",
                        entities = {
                                @EntityResult(entityClass = Category.class)
                        },
                        columns = {
                                @ColumnResult(name = "menu_count")
                        }
                ),
                /* 수동 엔티티 매핑 */
                @SqlResultSetMapping(
                        name = "categoryCountManualMapping",
                        entities = {
                                @EntityResult(
                                        entityClass = Category.class,
                                        fields = {
                                                @FieldResult(name = "categoryCode", column = "category_code"),
                                                @FieldResult(name = "categoryName", column = "category_name"),
                                                @FieldResult(name = "refCategoryCode", column = "ref_category_code")
                                        }
                                )
                        },
                        columns = {
                                @ColumnResult(name = "menu_count")
                        }
                )
        }
)
@Entity(name = "Section01Category")
@Table(name = "tbl_category")
public class Category {

    @Id
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }
}
