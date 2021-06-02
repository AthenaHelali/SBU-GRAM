package main.Common;

import java.util.ArrayList;

public class Like {
    private ArrayList<Profile> WhoLiked;
    private int numberOfLikes;
    public Like(){
        this.numberOfLikes=0;
        WhoLiked=new ArrayList<>();
    }

    public ArrayList<Profile> getWhoLiked() {
        return WhoLiked;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void LikePost() {
        this.numberOfLikes ++;
    }
    public void disLikePost() {
        this.numberOfLikes--;
    }

    public void AddWhoLiked(Profile profile) {
        WhoLiked.add(profile);
    }
}
