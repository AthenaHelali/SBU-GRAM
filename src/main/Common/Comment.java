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

    public void setWriterUsername(String writerUsername) {
        WriterUsername = writerUsername;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setDateAndTime(String dateAndTime) {
        DateAndTime = dateAndTime;
    }

    public void setMiliTime(Long miliTime) {
        MiliTime = miliTime;
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
