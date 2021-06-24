package main.Common.Message;

import main.Common.Account;

import java.util.Map;

public class LoginMessage implements Message {
    public static final long serialVersionUID = 32345678L;
    private String Username;
    private String Password;

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public LoginMessage(String username, String password) {
        Username = username;
        Password = password;
    }

    public boolean CheckPassword(Map<String, Account> profileMap) {
        if (profileMap.size() == 0)
            return false;

        else return profileMap.get(Username).getPassword().equals(Password);
    }

    public Account Handle(Map<String, Account> profileMap) {
        return profileMap.get(Username);
    }
}
