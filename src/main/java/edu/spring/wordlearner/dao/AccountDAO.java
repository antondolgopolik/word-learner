package edu.spring.wordlearner.dao;

import edu.spring.wordlearner.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

    public void create(String login, String password) {

    }

    public boolean accountExists(Account account) {
        return false;
    }

    public boolean loginExists(String login) {
        return false;
    }
}
