package main.Common.Message;

public class UpdateDataMessage implements Message{
    public static final long serialVersionUID = 112345678L;
    String Username;

    public String getUsername() {
        return Username;
    }

    public UpdateDataMessage(String username) {
        Username = username;
    }
}
