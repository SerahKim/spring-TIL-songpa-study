package com.crud.menu.model.service;

import com.crud.menu.model.dao.MenuMapper;
import com.crud.menu.model.dto.CategoryDTO;
import com.crud.menu.model.dto.MenuDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<MenuDTO> findAllMenu() {
        return menuMapper.findAllMenu();
    }

    public MenuDTO findMenuByCode(int code) {
        return menuMapper.findMenuByCode(code);
    }

    @Transactional // 자동으로 commit, rollback 등의 transaction 처리를 해줌
    public void registNewMenu(MenuDTO newMenu) {
        menuMapper.registNewMenu(newMenu);
    }

    @Transactional
    public void updateMenu(MenuDTO modifyMenu) {
        menuMapper.updateMenu(modifyMenu);
    }

    @Transactional
    public void deleteMenubyCode(int code) {
        menuMapper.deleteMenu(code);
    }

    public List<CategoryDTO> findAllCategory() {
        return menuMapper.findAllCategory();
    }


}
