package main.server;

import main.Common.Account;
import main.Common.OthersAccount;
import main.Common.Post;

public class API {
    //client side decides what to do according to return value
    public static synchronized boolean Like(Post post, Account WhoLiked){
        Post temp=null;
       for (Post posts:Server.AllProfiles.get(post.getWriterUsername()).getMyPosts()){
           if(posts.getTitle().equals(post.getTitle())){
               temp=posts;
               break;
           }
       }
        if(temp.getLike().getWhoLiked().contains(WhoLiked)){
            temp.getLike().disLikePost(WhoLiked);
            WhoLiked.RemoveYouLiked(temp);
            DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(post.getWriterUsername()));
            DataBase.getDataBase().UpdateProfile(WhoLiked);
            return false;
        }
        else{
            temp.getLike().LikePost(WhoLiked);
            WhoLiked.addYouLiked(temp);
            DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(post.getWriterUsername()));
            DataBase.getDataBase().UpdateProfile(WhoLiked);
            return true;
        }

    }
    public static void Follow(String FollowerUsername,String FollowedUsername){
        Account account=Server.AllProfiles.get(FollowedUsername);
        OthersAccount othersAccount=new OthersAccount(account.getFirstName(),account.getLastName(),account.getUsername(),account.getLocation()
        ,account.getBio(), account.getMyPosts(), account.getFollowers(), account.getFollowers(), account.getProfileImage());
        Server.AllProfiles.get(FollowerUsername).Follow(othersAccount);
        account=Server.AllProfiles.get(FollowedUsername);
        othersAccount=new OthersAccount(account.getFirstName(),account.getLastName(),account.getUsername(),account.getLocation()
                ,account.getBio(), account.getMyPosts(), account.getFollowers(), account.getFollowers(), account.getProfileImage());
        Server.AllProfiles.get(FollowedUsername).setFollowers(othersAccount);
        DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(FollowerUsername));
        DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(FollowedUsername));
    }
}
