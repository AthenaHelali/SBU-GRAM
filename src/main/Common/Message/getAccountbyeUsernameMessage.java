package main.Common.Message;

import main.Common.Account;
import main.Common.OthersAccount;

import java.util.Map;

public class getAccountbyeUsernameMessage implements Message {
    private String Username;
    public getAccountbyeUsernameMessage(String username) {
        Username = username;
    }
    public String getUsername() {
        return Username;
    }
    public OthersAccount Handle(Map<String,Account>map){
        OthersAccount othersAccount;
        Account account=map.get(Username);
        othersAccount=new OthersAccount(account.getFirstName(),account.getLastName(),account.getUsername(),account.getLocation(),account.getBio(),account.getMyPosts(),account.getFollowing(),account.getFollowers(),account.getProfileImage());
        return othersAccount;
    }

}
