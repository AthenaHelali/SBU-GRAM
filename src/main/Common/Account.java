package main.Common;

import main.Common.Message.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Account implements Serializable {
    private String FirstName;
    private String LastName;
    private String Username;
    private String password;
    private String location;
    private String bio;
    private String Email;
    private ArrayList<Post> MyPosts;
    private ArrayList<Account> Following;
    private ArrayList<Account> Followers;
    private ArrayList<Post> YouLiked;
    private byte[] profileImage;
    private Map<Account, List<Message>> directChat;

    public byte[] getProfileImage() {
        return profileImage;
    }


    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public Account(String firstName, String lastName, String username, String password, String email) {
        FirstName = firstName;
        LastName = lastName;
        Username = username;
        MyPosts = new ArrayList<>();
        Following = new ArrayList<>();
        Followers=new ArrayList<>();
        YouLiked = new ArrayList<>();
        this.password = password;
        Email = email;
        profileImage=null;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFollowers(Account account) {
        Followers.add(account);
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public String getBio() {
        return bio;
    }

    public ArrayList<Account> getFollowers() {
        return Followers;
    }

    public Map<Account, List<Message>> getDirectChat() {
        return directChat;
    }

    public ArrayList<Post> getYouLiked() {
        return YouLiked;
    }

    public void addYouLiked(Post post) {
        YouLiked.add(post);
    }

    public void RemoveYouLiked(Post post) {
        YouLiked.remove(post);
    }

    public void Repost(Post post) {
        MyPosts.add(post);
    }

    public void NewPost(Post post) {
        MyPosts.add(post);
    }

    public void Follow(Account user) {
        Following.add(user);
    }

    public void Unfollow(Account user) {
        Following.remove(user);
    }

    public void comment(Post post) {
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

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return Email;
    }

    public ArrayList<Post> getMyPosts() {
        return MyPosts;
    }

    public ArrayList<Account> getFollowing() {
        return Following;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Username.equals(account.Username);
    }


    @Override
    public int hashCode() {
        return Objects.hash(Username);
    }
}
