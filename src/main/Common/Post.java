package main.Common;

import java.util.List;

public class Post {
    private String title;
    private String WriterUsername;
    private Like like;
    private int repostNum;
    private List<Comment>comments;
    private String description;
    public Post(){}

    public Post(String title, String writerUsername, String description) {
        like=new Like();
        this.title = title;
        WriterUsername = writerUsername;
        this.description = description;
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

    public List<Comment> getComments() {
        return comments;
    }


}
