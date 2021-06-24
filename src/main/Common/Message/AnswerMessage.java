package main.Common.Message;

import main.Common.Account;
import main.Common.Comment;
import main.Common.OthersAccount;
import main.Common.Post;

import java.util.ArrayList;

public class AnswerMessage implements Message {
    public static final long serialVersionUID = 62345678L;
    private boolean value;
    private Account account;
    private ArrayList<Post> posts;
    private ArrayList<OthersAccount> othersAccounts;
    private ArrayList<Comment> comments;
    private OthersAccount otherAccount;
    private String Password;
    private int Followers;
    private int likesNumber;
    private int repostNum;
    private int CommentNumber;
    private byte[] ProfileImage;


    public void setRepostNum(int repostNum) {
        this.repostNum = repostNum;
    }

    public int getRepostNum() {
        return repostNum;
    }

    public void setLikesNumber(int likesNumber) {
        this.likesNumber = likesNumber;
    }

    public int getLikesNumber() {
        return likesNumber;
    }

    public void setCommentNumber(int commentNumber) {
        CommentNumber = commentNumber;
    }

    public int getCommentNumber() {
        return CommentNumber;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setOtherAccount(OthersAccount otherAccount) {
        this.otherAccount = otherAccount;
    }

    public OthersAccount getOtherAccount() {
        return otherAccount;
    }

    public void setProfileImage(byte[] profileImage) {
        ProfileImage = profileImage;
    }

    public byte[] getProfileImage() {
        return ProfileImage;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public ArrayList<OthersAccount> getOthersAccounts() {
        return othersAccounts;
    }

    public void setOthersAccounts(ArrayList<OthersAccount> othersAccounts) {
        this.othersAccounts = othersAccounts;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setFollowers(int followers) {
        Followers = followers;
    }

    public int getFollowers() {
        return Followers;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPassword() {
        return Password;
    }

}
