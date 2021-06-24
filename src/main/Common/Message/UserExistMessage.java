package main.Common.Message;

import main.Common.Account;

import java.util.Map;

public class UserExistMessage implements Message {
    private String Username;

    public UserExistMessage(String username) {
        Username = username;
    }

    public boolean Handle(Map<String, Account> account) {
        if (account.size() == 0)
            return false;
        else return account.containsKey(Username);
    }
}
