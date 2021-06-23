package main.Common.Message;

public class UnfollowMessage implements Message {
    public static final long serialVersionUID = 122345678L;
    private String Follower;
    private String UnfollowedUser;

    public UnfollowMessage(String follwer, String followedUser) {
        Follower = follwer;
        this.UnfollowedUser = followedUser;
    }

    public String getFollower() {
        return Follower;
    }


    public String getUnfollowedUser() {
        return UnfollowedUser;
    }
}

