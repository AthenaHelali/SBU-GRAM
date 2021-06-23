package main.Common.Message;
import main.Common.*;

import java.util.ArrayList;
import java.util.Map;

public class timelinePostsMessage implements Message {
    private String account;
    private ArrayList<Post>posts=new ArrayList<>();
    private Post p;

    public timelinePostsMessage(String  account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }
    public ArrayList<Post> Handle(Map<String,Account>map){
        Account Ac=map.get(account);
        for (OthersAccount ac:Ac.getFollowing()){
               for (Post post:map.get(ac.getUsername()).getMyPosts()) {
                   p=new Post(post.getTitle(),post.getWriterUsername(),post.getDescription());
                   p.setComments(post.getComments());
                   p.setWriterImage(post.getWriterImage());
                   p.setRepostNum(post.getRepostNum());
                   p.setLike(post.getLike());
                   p.setMiliTime(post.getMiliTime());
                   p.setDateAndTime(post.getDateAndTime());
                   p.setPostImage(post.getPostImage());
                   posts.add(p);
               }

            }
        for (Post post:map.get(account).getMyPosts()) {
            if(post.getWriterUsername().equals(account)){
            p=new Post(post.getTitle(),post.getWriterUsername(),post.getDescription());
            ArrayList<Comment>comments=new ArrayList<>(post.getComments());
            p.setComments(comments);
            p.setWriterImage(post.getWriterImage());
            p.setRepostNum(post.getRepostNum());
            Like like=new Like();
            like.setWhoLiked(post.getLike().getWhoLiked());
            like.setNumberOfLikes(post.getLike().getNumberOfLikes());
            p.setLike(like);
            p.setMiliTime(post.getMiliTime());
            p.setDateAndTime(post.getDateAndTime());
            p.setPostImage(post.getPostImage());
            posts.add(p);
        }}
        return posts;

    }
}
