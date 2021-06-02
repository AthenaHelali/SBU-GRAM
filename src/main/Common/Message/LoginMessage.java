package main.Common.Message;

import main.Common.Profile;

import java.util.Map;

public class LoginMessage implements Message{
    public static final long serialVersionUID = 32345678L;
    private String Username;
    private String Password;
    public boolean Handle(Map<String, Profile> profileMap) {
        Profile profile=profileMap.get(Username);
        if(profile.getPassword().equals(Password))
            return true;
        else return false;
    }
}
