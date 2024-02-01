package org.alex.controller;

import lombok.RequiredArgsConstructor;
import org.alex.entity.Item;
import org.alex.entity.ItemDepartmentType;
import org.alex.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/items")
public class AdminItemController {

    private final ItemService itemService;

    @GetMapping("")
    public String getItems(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "items_list";
    }

    @GetMapping("/add")
    public String addItemPage(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        model.addAttribute("itemDepartmentTypes", ItemDepartmentType.values());
        return "add_item";
    }

    @PostMapping("/add")
    public String addItem(@RequestParam("department") String department, @RequestParam("name") String
            name, @RequestParam("price") String price, Model model) {

        Item itemToAdd = Item.builder()
                .name(name)
                .price(Integer.parseInt(price))
                .itemDepartmentType(ItemDepartmentType.getById(department))
                .build();
        try {
            List<Item> items = itemService.addItemReturnAll(itemToAdd);
            String successMessage = "THE ITEM HAS BEEN ADDED";
            model.addAttribute("successMessage", successMessage);
            model.addAttribute("items", items);
            model.addAttribute("itemDepartmentTypes", ItemDepartmentType.values());
            return "add_item";
        } catch (Exception e) {
            List<Item> items = itemService.findAll();
            String errorMessage = "The item name you entered is already on the list";
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("items", items);
            model.addAttribute("itemDepartmentTypes", ItemDepartmentType.values());
        }

        return "add_item";

    }


    @GetMapping("/delete")
    public String removeItemPage(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        model.addAttribute("itemDepartmentTypes", ItemDepartmentType.values());
        return "remove_item";
    }

    @PostMapping("/delete")
    public String removeItem(@RequestParam("name") String name, Model model) {
        Item itemToRemove = Item.builder()
                .name(name)
                .build();
        List<Item> items = itemService.deleteItemReturnAll(itemToRemove);
        model.addAttribute("items", items);
        model.addAttribute("itemDepartmentTypes", ItemDepartmentType.values());
        return "remove_item";
    }

    @GetMapping("/update")
    public String updateItemPage(Model model) {
        List<Item> items = itemService.findAll();
        String duplicateItem = "The item you entered already exists";
        model.addAttribute("items", items);
        model.addAttribute("itemDepartmentTypes", ItemDepartmentType.values());
        model.addAttribute("errorMessage", duplicateItem);
        return "update_item";
    }

    @PostMapping("/update")
    public String updateItem(@RequestParam("name") String name, @RequestParam("price") String price, Model model) {
        Item itemToUpdate = Item.builder()
                .name(name)
                .price(Integer.parseInt(price))
                .build();
        List<Item> items = itemService.updateItemReturnAll(itemToUpdate);
        model.addAttribute("items", items);
        model.addAttribute("itemDepartmentTypes", ItemDepartmentType.values());
        return "update_item";

    }


}
