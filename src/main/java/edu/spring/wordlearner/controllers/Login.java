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
    public String index(Model model) {
        model.addAttribute("account", new Account());
        return "login";
    }

    @PostMapping("/logIn")
    public String logIn(@ModelAttribute("account") Account account) {
        if (accountDAO.exists(account.getLogin(), account.getPassword())) {
            System.err.println("Login...");
            return "redirect:/";
        } else {
            System.err.println("Check your login and password!");
            return "redirect:/login";
        }
    }
}
