package main.Common.Message;

import javafx.geometry.Pos;
import main.Common.Account;
import main.Common.Post;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class timelinePostsMessage implements Message {
    private Account account;
    private ArrayList<Post>posts=new ArrayList<>();

    public timelinePostsMessage(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
    public ArrayList<Post> Handle(ArrayList<Account>accounts){
        accounts=(ArrayList) accounts.stream().filter(a->a.getFollowers().contains(account)).collect(Collectors.toList());
        accounts.add(account);
        for (Account account:accounts){
            for (Post post:posts){
                posts.add(post);
            }

        }
        for (Post post:account.getMyPosts()){
            posts.add(post);
        }
        return posts;
    }
}
