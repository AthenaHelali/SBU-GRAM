package main.Common.Message;

import main.Common.Account;
import main.Common.Comment;
import main.Common.Post;

import java.util.ArrayList;
import java.util.Map;

public class NewCommentMessage implements Message {
    private Comment comment;
    private String PostTitle;
    private String PostWriter;

    public NewCommentMessage(Comment comment, String postTitle, String postWriter) {
        this.comment = comment;
        this.PostTitle = postTitle;
        this.PostWriter = postWriter;
    }

    public String getPostWriter() {
        return PostWriter;
    }

    public String getPostTitle() {
        return PostTitle;
    }

    public void Handle(Map<String, Account> map) {
        ArrayList<Post> posts = map.get(PostWriter).getMyPosts();
        for (Post post : posts) {
            if (post.getTitle().equals(PostTitle)) {
                post.AddComent(comment);
                break;
            }
        }


    }
}
