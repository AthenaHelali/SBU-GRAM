package main.server;

import main.Common.Account;
import main.Common.OthersAccount;
import main.Common.Post;

public class API {
    public synchronized static boolean Like(Post post, Account WhoLiked) {
        Post temp = null;
        for (Post posts : Server.AllProfiles.get(post.getWriterUsername()).getMyPosts()) {
            if (posts.getTitle().equals(post.getTitle())) {
                temp = posts;
                break;
            }
        }
        if (temp.getLike().getWhoLiked().contains(WhoLiked)) {
            temp.getLike().disLikePost(WhoLiked);
            WhoLiked.RemoveYouLiked(temp);
            DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(post.getWriterUsername()));
            DataBase.getDataBase().UpdateProfile(WhoLiked);
            return false;
        } else {
            temp.getLike().LikePost(WhoLiked);
            WhoLiked.addYouLiked(temp);
            DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(post.getWriterUsername()));
            DataBase.getDataBase().UpdateProfile(WhoLiked);
            return true;
        }

    }

    public synchronized static void Follow(String FollowerUsername, String FollowedUsername) {
        Account account = Server.AllProfiles.get(FollowedUsername);
        OthersAccount othersAccount = new OthersAccount(account.getFirstName(), account.getLastName(), account.getUsername(), account.getLocation()
                , account.getBio(), account.getMyPosts(), account.getFollowers(), account.getFollowers(), account.getProfileImage());
        if (!Server.AllProfiles.get(FollowerUsername).getFollowing().contains(othersAccount)) {
            Server.AllProfiles.get(FollowerUsername).Follow(othersAccount);
            account = Server.AllProfiles.get(FollowerUsername);
            othersAccount = new OthersAccount(account.getFirstName(), account.getLastName(), account.getUsername(), account.getLocation()
                    , account.getBio(), account.getMyPosts(), account.getFollowers(), account.getFollowers(), account.getProfileImage());
            Server.AllProfiles.get(FollowedUsername).setFollowers(othersAccount);
        }
        DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(FollowerUsername));
        DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(FollowedUsername));
    }

    public synchronized static void UnFollow(String FollowerUsername, String UnFollowedUsername) {
        for (OthersAccount account : Server.AllProfiles.get(FollowerUsername).getFollowing()) {
            if (account.getUsername().equals(UnFollowedUsername)) {
                Server.AllProfiles.get(FollowerUsername).getFollowing().remove(account);
                break;
            }
        }
        for (OthersAccount ac : Server.AllProfiles.get(UnFollowedUsername).getFollowers()) {
            if (ac.getUsername().equals(FollowerUsername)) {
                Server.AllProfiles.get(UnFollowedUsername).getFollowers().remove(ac);
                break;
            }
        }
        DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(FollowerUsername));
        DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(UnFollowedUsername));
    }
}
