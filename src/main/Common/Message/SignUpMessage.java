package main.Common.Message;

import main.Common.Profile;

import java.util.Map;

public class SignUpMessage implements Message {
    public static final long serialVersionUID = 82345678L;
    Profile profile;

    public SignUpMessage(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void Handle(Map<String, Profile> profileMap) {
        profileMap.put(profile.getUsername(),profile);

    }
}
