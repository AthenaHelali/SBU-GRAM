package main.Common;

import java.io.Serializable;

public class Comment implements Serializable {
    private String WriterUsername;
    private String descriptions;

    public Comment(String writerUsername, String descriptions) {
        WriterUsername = writerUsername;
        this.descriptions = descriptions;
    }

    public String getWriterUsername() {
        return WriterUsername;
    }

    public String getDescriptions() {
        return descriptions;
    }
}
