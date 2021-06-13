package main.Common;

import main.Common.Message.Message;

import java.io.Serializable;
import java.util.ArrayList;

public class OthersAccount implements Serializable {
    private String FirstName;
    private String LastName;
    private String Username;
    private String location;
    private String bio;
    private ArrayList<Post> Posts;
    private ArrayList<OthersAccount> Following;
    private ArrayList<OthersAccount> Followers;
    private byte[] profileImage;

    public OthersAccount(String firstName, String lastName, String username, String location, String bio, ArrayList<Post> posts,
                         ArrayList<OthersAccount> following, ArrayList<OthersAccount> followers, byte[] profileImage) {
        FirstName = firstName;
        LastName = lastName;
        Username = username;
        this.location = location;
        this.bio = bio;
        Posts = posts;
        Following = following;
        Followers = followers;
        this.profileImage = profileImage;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getUsername() {
        return Username;
    }

    public String getLocation() {
        return location;
    }

    public String getBio() {
        return bio;
    }

    public ArrayList<Post> getPosts() {
        return Posts;
    }

    public ArrayList<OthersAccount> getFollowing() {
        return Following;
    }

    public ArrayList<OthersAccount> getFollowers() {
        return Followers;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }
}
