package main.Common;

import main.Common.Message.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Profile implements Serializable{
    private String FirstName;
    private String LastName;
    private String Username;
    private String password;
    private String Email;
    private ArrayList<Post> MyPosts;
    private ArrayList<Profile>Following;
    private ArrayList<Profile>Followers;
    private ArrayList<Post>YouLiked;
    private Map<Profile,List<Message>> directChat;

    public Profile(String firstName, String lastName, String username, String password, int id, String email) {
        FirstName = firstName;
        LastName = lastName;
        Username = username;
        MyPosts=new ArrayList<>();
        Following=new ArrayList<>();
        Followers=new ArrayList<>();
        YouLiked=new ArrayList<>();
        this.password = password;
        Email = email;
    }

    public ArrayList<Post> getYouLiked() {
        return YouLiked;
    }

    public void addYouLiked(Post post) {
        YouLiked.add(post);
    }public void RemoveYouLiked(Post post) {
        YouLiked.remove(post);
    }

    public void Repost(Post post){
        MyPosts.add(post);
    }
    public void NewPost(Post post){MyPosts.add(post);}
    public void Follow(Profile user){
        Following.add(user);
    }
    public void Unfollow(Profile user){
        Following.remove(user);
    }
    public void comment(Post post){}

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

    public List<Profile> getFollowing() {
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
        Profile profile = (Profile) o;
        return Username.equals(profile.Username);
    }



    @Override
    public int hashCode() {
        return Objects.hash(Username);
    }
}
