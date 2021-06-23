package main.Common.Message;


public class getFollowersNumberMessage implements Message{
    private String Username;
    public getFollowersNumberMessage(String username) {
        Username = username;
    }
    public String getUsername() {
        return Username;
    }

}
