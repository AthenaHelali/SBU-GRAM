package main.Common.Message;

import main.Common.Post;
import main.Common.Profile;

public class LikeMessage implements Message{
    public static final long serialVersionUID = 22345678L;
    public Profile WhoLiked;
    public Post post;

    public LikeMessage(Profile whoLiked, Post post) {
        WhoLiked = whoLiked;
        this.post = post;
    }

    public void Handle() {

    }
}
