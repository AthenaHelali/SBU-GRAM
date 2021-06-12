package main.Common.Message;

import main.Common.Account;
import main.Common.OthersAccount;
import main.Common.Post;

import java.util.ArrayList;

public class AnswerMessage implements Message{
    public static final long serialVersionUID = 62345678L;
    private boolean value;
    private Account account;
    private ArrayList<Post>posts;
    private ArrayList<OthersAccount>othersAccounts;

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<OthersAccount> getOthersAccounts() {
        return othersAccounts;
    }

    public void setOthersAccounts(ArrayList<OthersAccount> othersAccounts) {
        this.othersAccounts = othersAccounts;
    }

    public Account getAccount() {
        return account;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public void Handle() {
    }
}
