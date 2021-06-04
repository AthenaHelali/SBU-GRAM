package main.Common.Message;

import main.Common.Account;

import java.util.Map;

public class SignUpMessage implements Message {
    public static final long serialVersionUID = 82345678L;
    Account account;

    public SignUpMessage(Account account) {
        this.account = account;
    }

    public Account getProfile() {
        return account;
    }

    public void Handle(Map<String, Account> profileMap) {
        profileMap.put(account.getUsername(), account);

    }
}
