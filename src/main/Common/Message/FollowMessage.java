package main.Common.Message;

public class FollowMessage implements Message{
    public static final long serialVersionUID = 12345678L;
    private String Follower;
    private String followedUser;

    public FollowMessage(String follwer, String followedUser) {
        Follower = follwer;
        this.followedUser = followedUser;
    }

    public String getFollower() {
        return Follower;
    }


    public String getFollowedUser() {
        return followedUser;
    }
}
