package main.Common.Message;

import main.Common.Profile;

import java.util.Map;

public class UserExistMessage implements Message{
   private String Username;

    public UserExistMessage(String username) {
        Username = username;
    }
    public boolean Handle(Map<String, Profile> profile) {
      if (profile.containsKey(Username))
          return true;
      else return false;

    }
}
