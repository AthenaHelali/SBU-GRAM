package main.Common.Message;
import main.Common.Account;
import main.Common.Post;

import java.util.Map;

public class repostMessage implements Message{
    private String PostTitle;
    private String PostWriterUsername;
    private String repostedUsername;

    public repostMessage(String postTitle, String postWriterUsername, String repostedUsername) {
        PostTitle = postTitle;
        PostWriterUsername = postWriterUsername;
        this.repostedUsername = repostedUsername;
    }

    public String getPostTitle() {
        return PostTitle;
    }

    public String getPostWriterUsername() {
        return PostWriterUsername;
    }

    public String getRepostedUsername() {
        return repostedUsername;
    }

    public void Handle(Map<String, Account>map){
        Post post=null;
        for (Post post1:map.get(PostWriterUsername).getMyPosts()){
            if(post1.getTitle().equals(PostTitle)){
                post=post1;
                break;
            }
        }
        post.setRepostNum(post.getRepostNum()+1);
        map.get(repostedUsername).getMyPosts().add(post);
    }
}
