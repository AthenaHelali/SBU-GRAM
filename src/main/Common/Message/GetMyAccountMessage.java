package main.Common.Message;

import main.Common.Account;
import main.Common.OthersAccount;
import main.Common.Post;

import java.util.Map;

public class GetMyAccountMessage implements Message {
    String Username;
    private Account account;

    public GetMyAccountMessage(String username) {
        Username = username;
    }

    public Account Handle(Map<String, Account> map) {
        Account temp = map.get(Username);

        account = new Account(temp.getFirstName(), temp.getLastName(), temp.getUsername(), temp.getPassword(), temp.getEmail(), temp.getPasswordQuestion());
        for (Post post : temp.getMyPosts()) {
            account.getMyPosts().add(post);
        }
        for (OthersAccount account : temp.getFollowers()) {
            this.account.getFollowers().add(account);
        }
        for (OthersAccount account : temp.getFollowing()) {
            this.account.getFollowing().add(account);
        }
        for (Post post : account.getYouLiked()) {
            this.account.getYouLiked().add(post);
        }
        return account;
    }


}