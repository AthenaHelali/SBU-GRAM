package main.Common;

import java.io.Serializable;

public class Comment implements Serializable {
    private String WriterUsername;
    private String descriptions;

    public String getDateAndTime() {
        return DateAndTime;
    }

    public Long getMiliTime() {
        return MiliTime;
    }

    private String DateAndTime;
    private Long MiliTime;

    public Comment(String writerUsername, String descriptions) {
        DateAndTime=DateTime.getTime();
        MiliTime=DateTime.getMilli();
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
