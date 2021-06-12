package main.server;

import main.Common.Account;
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
    public static synchronized Account Follow(Account account,String username){
        Server.AllProfiles.get(username).setFollowers(account);
        account.Follow(Server.AllProfiles.get(username));
        DataBase.getDataBase().UpdateProfile(account);
        DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(username));
        return Server.AllProfiles.get(username);
    }
}
