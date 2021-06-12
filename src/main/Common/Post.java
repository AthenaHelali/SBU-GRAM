package main.Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Post implements Serializable {
    private String title;
    private String WriterUsername;
    private Like like;
    private int repostNum;
    private ArrayList<Comment> comments;
    private String description;
    private byte[]writerImage;
    private byte[]PostImage=null;
    public Post(){}

    public Post(String title, String writerUsername, String description) {
        like=new Like();
        this.title = title;
        WriterUsername = writerUsername;
        this.description = description;
        comments=new ArrayList<>();
    }

    public byte[] getPostImage() {
        return PostImage;
    }

    public void setPostImage(byte[] postImage) {
        PostImage = postImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriterUsername(String writerUsername) {
        WriterUsername = writerUsername;
    }

    public byte[] getWriterImage() {
        return writerImage;
    }

    public void setWriterImage(byte[] writerImage) {
        this.writerImage = writerImage;
    }

    public String getTitle() {
        return title;
    }

    public Like getLike() {
        return like;
    }

    public String getWriterUsername() {
        return WriterUsername;
    }

    public int getRepostNum() {
        return repostNum;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }


}
