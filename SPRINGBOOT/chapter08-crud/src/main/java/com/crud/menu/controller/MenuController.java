package com.crud.menu.controller;

import com.crud.menu.model.dto.CategoryDTO;
import com.crud.menu.model.dto.MenuDTO;
import com.crud.menu.model.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /*---------전체 메뉴 조회---------*/
    @GetMapping("/list")
    public String findMenuList(Model model) {

        List<MenuDTO> menuList = menuService.findAllMenu();

//        for (MenuDTO menus : menuList) {
//            System.out.println(menus);
//        }

        model.addAttribute("menuList", menuList);

        return "menu/list";
    }

    /*---------메뉴 상세 조회---------*/
    @GetMapping("/search")
    public void searchPage() {}

    @PostMapping("/search")
    public String findMenuByCode(@RequestParam("code") int code, Model model) {
        MenuDTO menu = menuService.findMenuByCode(code);

        model.addAttribute("menu", menu);

        return "menu/search";
    }

    /*---------메뉴 등록---------*/
    @GetMapping("/regist")
    public void registPage() {}

    @PostMapping("/regist")
    public String registMenu(MenuDTO newMenu, RedirectAttributes rttr) {
        menuService.registNewMenu(newMenu);

        rttr.addFlashAttribute("successMessage", "신규 메뉴 등록에 성공하셨습니당당구리");

        return "redirect:/menu/list";
    }

    /*---------메뉴 수정---------*/
    @GetMapping("/update")
    public String updatePage(Model model) {

        List<MenuDTO> menuList = menuService.findAllMenu();

        model.addAttribute("menuList", menuList);

        return "menu/update";
    }

    @PostMapping("/update")
    public String updateMenu(MenuDTO modifyMenu, RedirectAttributes rttr) {
        menuService.updateMenu(modifyMenu);

        rttr.addFlashAttribute("successMessage", "메뉴 변경 성공!");

        return "redirect:/menu/list";
    }

    /*---------메뉴 삭제---------*/
    @GetMapping("/delete")
    public String deletePage(Model model) {

        List<MenuDTO> menuList = menuService.findAllMenu();

        model.addAttribute("menuList", menuList);

        return "menu/delete";
    }

    @PostMapping("/delete")
    public String deleteMenuByCode(@RequestParam("code") int code ,RedirectAttributes rttr) {

        menuService.deleteMenubyCode(code);

        rttr.addFlashAttribute("successMessage", "메뉴 삭제 성공!");

        return "redirect:/menu/list";
    }

    /*---------카테고리 조회---------*/
    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    @ResponseBody // 리턴하는 값을 페이지가 아닌 데이터로 응답하기 위한 어노테이션
    public List<CategoryDTO> findCategoryList() {

        menuService.findAllCategory().forEach(System.out::println);

        return menuService.findAllCategory();
    }
}
