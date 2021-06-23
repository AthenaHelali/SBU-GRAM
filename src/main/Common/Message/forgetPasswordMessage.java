package main.Common.Message;

import main.Common.Account;

import java.util.Map;

public class forgetPasswordMessage implements Message {
    String Username;
    String Question;
    public forgetPasswordMessage(String username, String question) {
        Username = username;
        Question = question;
    }

    public String getUsername() {
        return Username;
    }

    public String getQuestion() {
        return Question;
    }

    public boolean Handle(Map<String, Account>map){
        if(map.get(Username).getPasswordQuestion().equals(Question))
            return true;
        else return false;
    }
}
