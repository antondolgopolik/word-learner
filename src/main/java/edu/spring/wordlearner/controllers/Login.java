package edu.spring.wordlearner.controllers;

import edu.spring.wordlearner.dao.AccountDAO;
import edu.spring.wordlearner.models.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class Login {
    private final AccountDAO accountDAO;

    public Login(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @GetMapping
    public String login(Model model) {
        model.addAttribute("account", new Account());
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute("account") Account account) {
        if (accountDAO.accountExists(account)) {
            System.err.println("Login...");
            return "redirect:/";
        } else {
            System.err.println("Account doesn't exist!");
            return "redirect:/login";
        }
    }
}
