package main.Common;

import java.io.Serializable;
import java.util.ArrayList;

public class Like implements Serializable {
    private ArrayList<Account> WhoLiked;
    private int numberOfLikes;

    public Like() {
        this.numberOfLikes = 0;
        WhoLiked = new ArrayList<>();
    }

    public ArrayList<Account> getWhoLiked() {
        return WhoLiked;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setWhoLiked(ArrayList<Account> whoLiked) {
        WhoLiked = whoLiked;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public void LikePost(Account account) {
        this.numberOfLikes++;
        WhoLiked.add(account);
    }

    public void disLikePost(Account account) {
        this.numberOfLikes--;
        WhoLiked.remove(account);
    }
}
