package main.Common.Message;

import main.Common.Account;

import java.util.Map;

public class UpdateProfileMessage implements Message {
    public static final long serialVersionUID = 823456790L;
    public Account account;
    public UpdateProfileMessage(Account account) {
        this.account=new Account(account.getFirstName(), account.getLastName(), account.getUsername(), account.getPassword(), account.getEmail(),account.getPasswordQuestion());
        this.account.setLocation(account.getLocation());
        this.account.setBio(account.getBio());
        this.account.setProfileImage(account.getProfileImage());
        this.account.setFollowing(account.getFollowing());
        this.account.setFollowers(account.getFollowers());
        this.account.setYouLiked(account.getYouLiked());
        this.account.setMyPosts(account.getMyPosts());
        this.account.setPassword(account.getPassword());
    }
    public void Handle(Map<String,Account>map){
        map.replace(account.getUsername(),account);
    }
    public Account getAccount() {
        return account;
    }
}
