package main.Common.Message;
import main.Common.Account;

import java.util.ArrayList;
import java.util.Map;

public class GetMyAccountMessage implements Message{
    private Account Myaccount;

    private String Username;
    public GetMyAccountMessage(String username) {
        Username = username;
    }
    public Account Handle(Map<String, Account>map){
        Account account=map.get(Username);
        this.Myaccount=new Account(account.getFirstName(), account.getLastName(), account.getUsername(), account.getPassword(), account.getEmail());
        Myaccount.setLocation(account.getLocation());
        Myaccount.setBio(account.getBio());
        Myaccount.setProfileImage(account.getProfileImage());
        Myaccount.setFollowing(new ArrayList<>(account.getFollowing()));
        Myaccount.setFollowers(new ArrayList<>(account.getFollowers()));
        Myaccount.setYouLiked(new ArrayList<>(account.getYouLiked()));
        Myaccount.setMyPosts(new ArrayList<>(account.getMyPosts()));
        Myaccount.setPassword(account.getPassword());

        return Myaccount;
    }



}
