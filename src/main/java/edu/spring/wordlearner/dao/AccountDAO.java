package edu.spring.wordlearner.dao;

import edu.spring.wordlearner.db.Database;
import edu.spring.wordlearner.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountDAO {
    private final Connection connection;

    @Autowired
    public AccountDAO(Database database) {
        connection = database.getConnection();
    }

    public void create(String login, String password) {
        try {
            String sql = "INSERT INTO accounts VALUES(?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account read(String login) {
        Account account = null;
        try {
            // Prepare statement
            String sql = "SELECT * FROM accounts WHERE login=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            // SQL request
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account(login, resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public boolean exists(String login) {
        try {
            // Prepare statement
            String sql = "SELECT * FROM accounts WHERE login=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            // SQL request
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean exists(String login, String password) {
        try {
            // Prepare statement
            String sql = "SELECT * FROM accounts WHERE login=? AND password=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            // SQL request
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
