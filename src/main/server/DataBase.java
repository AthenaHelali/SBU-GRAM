package main.server;

import main.Common.Profile;
import java.io.*;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DataBase {
    private final String File_SUFFIX = ".txt";
    private final String File_PREFIX = ".txt";
    Set<String> Username = new HashSet<>();
    private String ProfileDirectory;
    private ObjectOutputStream objectOutputStream = null;
    private ObjectInputStream objectInputStream = null;
    File usernamesDir = new File("src/main.Client.server/Usernames.txt");
    Scanner scanner;
    //singletone class
    private DataBase() {}

    private static DataBase dataBase = new DataBase();

    public static DataBase getDataBase() {
        return dataBase;
    }

    public void initMap() {
        Server.AllProfiles = new ConcurrentHashMap<>();
        try {
            Scanner scanner = new Scanner(usernamesDir);
            while (scanner.hasNextLine()) {
                String string = scanner.nextLine().trim();
                Username.add(string);
            }
            //read profile
            for (String string : Username) {
                Server.AllUsernames.add(string);
                ProfileDirectory = File_PREFIX + string + File_SUFFIX;
                objectInputStream = new ObjectInputStream(new FileInputStream(ProfileDirectory));
                Profile profile = (Profile) objectInputStream.readObject();
                Server.AllProfiles.put(string, profile);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
//Save new Profile in new txt file
    public synchronized void SaveProfile(Profile profile) {
        try {
            ProfileDirectory = File_PREFIX + profile.getUsername() + File_SUFFIX;
            Formatter formatter = new Formatter(ProfileDirectory);
            formatter.format(profile.getUsername() + "\n");
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(ProfileDirectory));
            objectOutputStream.writeObject(profile);
            objectOutputStream.close();
            Server.AllUsernames.add(profile.getUsername());
            Server.AllProfiles.put(profile.getUsername(),profile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//Rewrite profile
    public synchronized void UpdateProfile(Profile profile) {
        ProfileDirectory = File_PREFIX + profile.getUsername() + File_SUFFIX;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(ProfileDirectory));
            objectOutputStream.writeObject(profile);
            objectOutputStream.close();
            Server.AllProfiles.replace(profile.getUsername(), profile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
