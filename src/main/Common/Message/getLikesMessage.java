package main.Common.Message;

import main.Common.Account;
import main.Common.Post;

import java.util.Map;

public class getLikesMessage implements Message{
    private String PostTitle;
    private String WriterUsername;

    public getLikesMessage(String postTitle, String writerUsername) {
        PostTitle = postTitle;
        WriterUsername = writerUsername;
    }
    public int Handle(Map<String, Account>map){
        for (Post post:map.get(WriterUsername).getMyPosts()){
            if(post.getTitle().equals(PostTitle)){
                return post.getLike().getNumberOfLikes();
            }
        }
        return 0;
    }

}
