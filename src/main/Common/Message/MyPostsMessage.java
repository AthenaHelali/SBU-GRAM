package main.Common.Message;

public class MyPostsMessage implements Message {
    private String Username;

    public String getUsername() {
        return Username;
    }

    public MyPostsMessage(String username) {
        Username = username;
    }
}