package main.Common.Message;

import main.Common.Account;
import main.Common.OthersAccount;
import main.Common.Post;

import java.util.ArrayList;
import java.util.Map;

public class getAccountbyeUsernameMessage implements Message {
    private String Username;

    public getAccountbyeUsernameMessage(String username) {
        Username = username;
    }

    public String getUsername() {
        return Username;
    }

    public OthersAccount Handle(Map<String, Account> map) {
        OthersAccount othersAccount;
        Account account = map.get(Username);
        ArrayList<OthersAccount> followers = new ArrayList<>();
        for (OthersAccount other : account.getFollowers())
            followers.add(other);
        ArrayList<OthersAccount> following = new ArrayList<>();

        for (OthersAccount other : account.getFollowing())
            following.add(other);
        ArrayList<Post> MyPosts = new ArrayList<>();
        for (Post post : account.getMyPosts()) {
            Post p = new Post(post.getTitle(), post.getWriterUsername(), post.getDescription());
            p.setComments(post.getComments());
            p.setWriterImage(post.getWriterImage());
            p.setRepostNum(post.getRepostNum());
            p.setLike(post.getLike());
            p.setMiliTime(post.getMiliTime());
            p.setDateAndTime(post.getDateAndTime());
            p.setPostImage(post.getPostImage());
            MyPosts.add(post);
        }
        othersAccount = new OthersAccount(account.getFirstName(), account.getLastName(), account.getUsername(), account.getLocation(), account.getBio(), MyPosts, following, followers, account.getProfileImage());
        return othersAccount;
    }

}
