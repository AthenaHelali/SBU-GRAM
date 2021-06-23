package main.Common.Message;

import main.Common.Account;
import main.Common.Post;

import java.util.Map;

public class GetRepostNumberMessage implements Message{
    private String PostTitle;
    private String WriterUsername;

    public GetRepostNumberMessage(String postTitle, String writerUsername) {
        PostTitle = postTitle;
        WriterUsername = writerUsername;
    }
    public int Handle(Map<String, Account>map){
        for (Post post:map.get(WriterUsername).getMyPosts()){
            if(post.getTitle().equals(PostTitle)){
                return post.getRepostNum();
            }

        }
        return 0;
    }
}
