package main.server;

import main.Common.Post;
import main.Common.Profile;

import java.util.stream.Collectors;

public class API {
    //client side decides what to do according to return value
    public static boolean Like(Post post, Profile WhoLiked){
        Post temp;
        temp=Server.AllProfiles.get(post.getWriterUsername()).getMyPosts().stream().filter(a->a.getTitle().equals(post.getTitle())).collect(Collectors.toList()).get(0);
        if(temp.getLike().getWhoLiked().contains(WhoLiked)){
            temp.getLike().getWhoLiked().remove(WhoLiked);
            temp.getLike().disLikePost();
            WhoLiked.RemoveYouLiked(temp);
            DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(post.getWriterUsername()));
            DataBase.getDataBase().UpdateProfile(WhoLiked);
            return false;
        }
        else{
            temp.getLike().LikePost();
            temp.getLike().AddWhoLiked(WhoLiked);
            WhoLiked.addYouLiked(temp);
            DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(post.getWriterUsername()));
            DataBase.getDataBase().UpdateProfile(WhoLiked);
            return true;
        }

    }
}
