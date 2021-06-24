package main.Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OthersAccount that = (OthersAccount) o;
        return Objects.equals(Username, that.Username);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(FirstName, LastName, Username, location, bio, Posts, Following, Followers);
        result = 31 * result + Arrays.hashCode(profileImage);
        return result;
    }
}
