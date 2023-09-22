package org.alex.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.alex.common.Session;
import org.alex.entity.Credentials;
import org.alex.service.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


@Controller
@RequiredArgsConstructor
public class CredentialController {

    private final SecurityService securityService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpServletResponse response) throws NoSuchAlgorithmException, NoSuchProviderException {

        Session session = securityService.authenticate(new Credentials(email, password));
        if (session != null) {
            Cookie cookie = new Cookie("user-token", session.getToken());
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            return "redirect:/user/items";
        }
        String errorMessage = "Wrong credentials";
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("email") String email, @RequestParam("password") String password, Model model) throws NoSuchAlgorithmException, NoSuchProviderException {

        String message = securityService.userRegistration(email, password);
        String emptyCredentials = "The email or password cannot be empty";
        String duplicateEmail = "The email you entered is already registered, please proceed to log in page";

        if (message.equals(emptyCredentials)) {
            model.addAttribute("errorMessage", emptyCredentials);
            return "register";
        }
        if (message.equals(duplicateEmail)) {
            model.addAttribute("errorMessage", duplicateEmail);
            return "register";
        }
        model.addAttribute("successMessage", message);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    String token = cookie.getValue();
                    securityService.logout(token);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
        return "redirect:/login";
    }
}
