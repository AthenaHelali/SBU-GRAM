package main.Common.Message;

public class GetProfileImageMessage implements Message {
    private String Username;
    private byte[] image;

    public GetProfileImageMessage(String username) {
        Username = username;
    }

    public String getUsername() {
        return Username;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }
}
