package com.associationmapping.section03.bidirection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BiDirectionService {

    private BiDirectionRepository biDirectionRepository;

    public BiDirectionService(BiDirectionRepository biDirectionRepository) {
        this.biDirectionRepository = biDirectionRepository;
    }

    @Transactional
    public Menu findMenu(int menuCode) {
        return biDirectionRepository.findMenu(menuCode);
    }
    @Transactional
    public Category findCategory(int categoryCode) {
        Category category = biDirectionRepository.findCategory(categoryCode);
        // 필요할 때 menuList를 select하는 쿼리문 발생
        // Lazy 로딩 -> eager 로딩
        System.out.println(category.getMenuList()); // Transactional 처리를 하지 않고 menuList(지연 연관 객체)를 조회하게 되면 P.C가 닫혀 있어 접근할 수 없다.
        System.out.println(category.getMenuList().get(0).getCategory());
        return category;
    }
}
