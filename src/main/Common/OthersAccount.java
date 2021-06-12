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
    private ArrayList<Account> Following;
    private ArrayList<Account> Followers;
    private byte[] profileImage;

    public OthersAccount(String firstName, String lastName, String username, String location, String bio, ArrayList<Post> posts,
                         ArrayList<Account> following, ArrayList<Account> followers, byte[] profileImage) {
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

    public ArrayList<Account> getFollowing() {
        return Following;
    }

    public ArrayList<Account> getFollowers() {
        return Followers;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }
}
