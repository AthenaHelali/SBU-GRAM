package main.Common.Message;

import main.Common.Account;

public class FollowMessage implements Message{
    public static final long serialVersionUID = 12345678L;
    private Account Follwer;
    private String followedUser;

    public FollowMessage(Account follwer, String followedUser) {
        Follwer = follwer;
        this.followedUser = followedUser;
    }

    public Account getFollwer() {
        return Follwer;
    }

    public String getFollowedUser() {
        return followedUser;
    }
}
