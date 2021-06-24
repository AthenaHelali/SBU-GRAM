package main.Common.Message;

import main.Common.Post;

public class NewPostMessage implements Message {
    public static final long serialVersionUID = 52345678L;
    Post newPost;
    boolean value;

    public NewPostMessage(Post newPost) {
        this.newPost = newPost;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public Post getNewPost() {
        return newPost;
    }

    public boolean getValue() {
        return value;
    }

}
