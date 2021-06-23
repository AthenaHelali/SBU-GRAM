package main.Common.Message;

import main.Common.Account;
import main.Common.Comment;
import main.Common.Post;

import java.util.ArrayList;
import java.util.Map;

public class getCommentsMessage implements Message {
    private String PostWriterUsername;
    private String PostTitle;
    private ArrayList<Comment>comments=new ArrayList<>();
    Post p;

    public getCommentsMessage(String postWriterUsername, String postTitle) {
        PostWriterUsername = postWriterUsername;
        PostTitle = postTitle;
    }

    public String getPostWriterUsername() {
        return PostWriterUsername;
    }

    public String getPostTitle() {
        return PostTitle;
    }
    public ArrayList<Comment>Handle(Map<String, Account>map){
        ArrayList<Post>posts=map.get(PostWriterUsername).getMyPosts();
        for (Post post:posts){
            if(post.getTitle().equals(PostTitle)) {
                p=post;
                break;
            }
        }
        for (Comment comment:p.getComments()){
            Comment temp=new Comment(comment.getWriterUsername(),comment.getDescriptions());
            temp.setDateAndTime(comment.getDateAndTime());
            temp.setMiliTime(temp.getMiliTime());
            comments.add(temp);
        }
        return comments;
    }
}
