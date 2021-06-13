package main.Common.Message;
import main.Common.Account;
import main.Common.Post;
import java.util.ArrayList;
import java.util.Map;

public class timelinePostsMessage implements Message {
    private String account;
    private ArrayList<Post>posts=new ArrayList<>();

    public timelinePostsMessage(String  account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }
    public ArrayList<Post> Handle(Map<String,Account>map){
        ArrayList<Account>accounts=new ArrayList<>(map.values());
        ArrayList<String>Usernames;
        for (Account ac:accounts){
           if(ac.getFollowers().stream().map(a->a.getUsername()).anyMatch(a->a.equals(account))){
               for (Post post:ac.getMyPosts())
                   posts.add(post);

            }
        }
        for (Post post:map.get(account).getMyPosts())
            posts.add(post);
        return posts;

    }
}
