package com.springdatajpa.menu.service;

import com.springdatajpa.menu.dto.CategoryDTO;
import com.springdatajpa.menu.dto.MenuDTO;
import com.springdatajpa.menu.entity.Category;
import com.springdatajpa.menu.entity.Menu;
import com.springdatajpa.menu.repository.CategoryRepository;
import com.springdatajpa.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // final 붙은 필드만 골라서 생성자를 자동 생성 -> 의존성을 안정적으로 주입하고, 코드를 간결하게 유지해줌.
public class MenuService {

    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    // Optional 타입 : NullPointException을 방지하기 위한 타입
    /*
    Integer(Wrapper Class) : Null 값을 담을 수 있기 때문에 Null을 가질 수 있는 값은 Wrapper 클래스 사용
    int(Init type) : Null 값을 담을 수 없다.
    */
    /* findById */
    public MenuDTO findMenuByMenuCode(int menuCode) {
        Menu foundMenu = menuRepository.findById(menuCode).orElseThrow(IllegalAccessError::new);

        // modelMapper를 통해 엔티티를 DTO로 변환
        return modelMapper.map(foundMenu, MenuDTO.class);
    }

    /* findAll : Sort*/
    public List<MenuDTO> findMenuList() {
        List<Menu> menuList = menuRepository.findAll(Sort.by("menuCode").descending());

        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class)) // List에 담긴 Menu를 MenuDTO 타입으로 변환
                .toList(); // stream -> list로 변환
    }

    /* findAll : Pageable */
    public Page<MenuDTO> findMenuList(Pageable pageable) {
        pageable = PageRequest.of(
                // pagealbe은 0부터 시작한다.
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("menuCode").descending()
        );

        Page<Menu> menuList = menuRepository.findAll(pageable);

        return menuList.map(menu -> modelMapper.map(menu, MenuDTO.class));
    }

    /* Query Method */
    public List<MenuDTO> findByMenuPrice(Integer menuPrice) {

//        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThanOrderByMenuPrice(menuPrice);
        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(
                menuPrice,
                Sort.by("menuPrice").descending()
        );

        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .toList();
    }

    /* JPQL or Native Query */
    public List<CategoryDTO> findAllCategory() {
        List<Category> categoryList = categoryRepository.findAllCategory();

        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
    }

    /* save */
    @Transactional
    public void registMenu(MenuDTO menuDTO) {

        // 내부적으로 persist됨.
        menuRepository.save(modelMapper.map(menuDTO, Menu.class));
    }

    /* 수정(엔티티 객체의 필드 값 변경) */
    @Transactional
    public void modifyMenu(MenuDTO menuDTO) {
        Menu foundMenu = menuRepository.findById(menuDTO.getMenuCode()).orElseThrow(IllegalArgumentException::new);

        /* setter 사용 지양, 기능에 맞는 메소드 정의해서 사용 */
        foundMenu.modifyMenuName(menuDTO.getMenuName());
    }

    @Transactional
    public void deleteMenu(Integer menuCode) {
        menuRepository.deleteById(menuCode);
    }
}
