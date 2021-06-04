package main.Common.Message;

import main.Common.Account;
import main.Common.Post;

public class LikeMessage implements Message{
    public static final long serialVersionUID = 22345678L;
    public Account WhoLiked;
    public Post post;

    public LikeMessage(Account whoLiked, Post post) {
        WhoLiked = whoLiked;
        this.post = post;
    }

    public void Handle() {

    }
}
