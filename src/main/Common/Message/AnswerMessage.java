package main.Common.Message;

import main.Common.Account;

public class AnswerMessage implements Message{
    public static final long serialVersionUID = 62345678L;
    private boolean value;
    private Account account;

    public void setProfile(Account account) {
        this.account = account;
    }

    public Account getProfile() {
        return account;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }


    public void Handle() {
    }
}
