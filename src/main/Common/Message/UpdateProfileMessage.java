package main.Common.Message;

import main.Common.Account;

import java.io.Serializable;

public class UpdateProfileMessage implements Message {
    private Account account;

    public UpdateProfileMessage(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
