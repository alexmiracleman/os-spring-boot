package org.alex.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.alex.common.Session;
import org.alex.entity.Item;
import org.alex.entity.ItemDepartmentType;
import org.alex.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserItemController {

    private final ItemService itemService;

    @GetMapping("/items")
    public String ItemsPage(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        model.addAttribute("itemDepartmentTypes", ItemDepartmentType.values());
        return "user_items_list";
    }

    @GetMapping("/cart")
    public String getCart(Model model, HttpServletRequest request) {
        Session session = (Session) request.getAttribute("session");
        List<Item> items = session.getCart();
        model.addAttribute("itemDepartmentTypes", ItemDepartmentType.values());
        model.addAttribute("items", items);
        return "user_cart";
    }

    @GetMapping("/cart/add")
    public String addItem(@RequestParam("id") String id, HttpServletRequest request, Model model) {
        Session session = (Session) request.getAttribute("session");
        List<Item> cart = session.getCart();
        List<Item> items = itemService.findAll();
        for (Item item : items) {
            if (item.getId() == parseInt(id)) {
                cart.add(item);
            }
        }
        String successMessage = "THE ITEM HAS BEEN ADDED TO YOUR CART";
        model.addAttribute("successMessage", successMessage);
        model.addAttribute("items", items);
        return "user_items_list";
    }

    @GetMapping("cart/remove")
    public String removeItem(@RequestParam("id") String id, HttpServletRequest request, Model model) {
        Session session = (Session) request.getAttribute("session");
        List<Item> cart = session.getCart();
        Item item = itemService.findByIdInCart(cart, parseInt(id));
        if (item != null) {
            cart.remove(item);
        }
        String successMessage = "THE ITEM HAS BEEN REMOVED FROM YOUR CART";
        List<Item> items = session.getCart();
        model.addAttribute("items", items);
        model.addAttribute("itemDepartmentTypes", ItemDepartmentType.values());
        model.addAttribute("successMessage", successMessage);
        return "user_cart";
    }
}
