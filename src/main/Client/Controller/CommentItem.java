package main.Client.Controller;

import javafx.scene.control.ListCell;
import main.Common.Comment;
import java.io.IOException;

public class CommentItem extends ListCell<Comment> {
    @Override
    public void updateItem(Comment comment, boolean empty) {
        super.updateItem(comment, empty);
        if (comment != null) {
            try {
                setGraphic(new CommentItemController(comment).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
