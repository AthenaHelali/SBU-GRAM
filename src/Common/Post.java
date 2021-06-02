package Common;

import java.util.List;

public class Post {
    private String title;
    private int WriterID;
    private String WriterUsername;
    private int Like;
    private int repostNum;
    private List<Comment>comments;
    private String description;

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

    public int getWriterID() {
        return WriterID;
    }

    public String getWriterUsername() {
        return WriterUsername;
    }

    public int getLike() {
        return Like;
    }

    public int getRepostNum() {
        return repostNum;
    }

    public List<Comment> getComments() {
        return comments;
    }


}
