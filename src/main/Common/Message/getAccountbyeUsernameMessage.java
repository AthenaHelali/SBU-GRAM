package main.Common.Message;

import main.Common.Account;

public class getAccountbyeUsernameMessage implements Message {
    private String Username;
    public getAccountbyeUsernameMessage(String username) {
        Username = username;
    }
    public String getUsername() {
        return Username;
    }

}
