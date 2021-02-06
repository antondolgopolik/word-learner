package edu.spring.wordlearner.controllers;

import edu.spring.wordlearner.dao.AccountDAO;
import edu.spring.wordlearner.dto.AccountDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class Registration {
    private final AccountDAO accountDAO;

    public Registration(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("account", new AccountDTO());
        return "registration";
    }

    @PostMapping
    public String signUp(@ModelAttribute("account") AccountDTO accountDTO) {
        if (accountDTO.getPassword().equals(accountDTO.getRepeatedPassword())) {
            accountDAO.create(accountDTO.getLogin(), accountDTO.getPassword());
        } else {
            System.err.println("Passwords differ!");
        }
        return "redirect:/registration";
    }
}
