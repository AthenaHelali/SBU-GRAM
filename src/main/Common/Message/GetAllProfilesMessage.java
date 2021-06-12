package main.Common.Message;

import main.Common.Account;
import main.Common.OthersAccount;

import java.util.ArrayList;

public class GetAllProfilesMessage implements Message {

    public ArrayList<OthersAccount> Handle(ArrayList<Account>accounts){
        ArrayList<OthersAccount> othersAccounts=new ArrayList<>();
        for (Account account:accounts){
            othersAccounts.add(new OthersAccount(account.getFirstName(),account.getLastName(), account.getUsername(), account.getLocation(),
                    account.getBio(), account.getMyPosts(), account.getFollowing(),account.getFollowers(),account.getProfileImage()));
        }
        return othersAccounts;
    }
}
