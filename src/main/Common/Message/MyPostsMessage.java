package main.Common.Message;

import main.Common.Post;

import java.util.ArrayList;

public class MyPostsMessage implements Message {
    private String Username;

    public String getUsername() {
        return Username;
    }

    public MyPostsMessage(String username) {
        Username = username;
    }
}
