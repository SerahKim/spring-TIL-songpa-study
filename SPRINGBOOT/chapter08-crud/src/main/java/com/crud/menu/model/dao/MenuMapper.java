package com.crud.menu.model.dao;

import com.crud.menu.model.dto.CategoryDTO;
import com.crud.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<MenuDTO> findAllMenu();

    MenuDTO findMenuByCode(int code);

    void registNewMenu(MenuDTO newMenu);

    void updateMenu(MenuDTO modifyMenu);

    void deleteMenu(int code);

    List<CategoryDTO> findAllCategory();
}
