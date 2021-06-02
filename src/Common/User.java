package Common;

import Message.Message;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class User implements Serializable {
    private String FirstName;
    private String LastName;
    private String Username;
    private String password;
    private final int id;
    private String Email;
    private List<Post> MyPosts;
    private List<User>Following;
    private Map<User,List<Message>> directChat;

    public User(String firstName, String lastName, String username, String password, int id, String email) {
        FirstName = firstName;
        LastName = lastName;
        Username = username;
        this.password = password;
        this.id = id;
        Email = email;
    }

    public void Repost(Post post){
        MyPosts.add(post);
    }
    public void NewPost(){}
    public void Follow(User user){
        Following.add(user);
    }
    public void Unfollow(User user){
        Following.remove(user);
    }
    public void comment(Post post){

    }
}
