package main.Common.Message;

import main.Common.Account;
import main.Common.Post;

import java.util.Map;

public class getCommentNumberMessage implements Message {
    String postTitle;
    String postWriter;

    public getCommentNumberMessage(String postTitle, String postWriter) {
        this.postTitle = postTitle;
        this.postWriter = postWriter;
    }

    public int Handle(Map<String, Account> map) {
        Post p=null;
        for (Post post : map.get(postWriter).getMyPosts()) {
            if (post.getTitle().equals(postTitle)) {
                p = post;
                break;
            }

        }
        return p.getComments().size();
    }
}
