package main.server;

import main.Common.Account;

import java.io.*;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DataBase {
    private final String File_SUFFIX = ".txt";
    Set<String> Username = new HashSet<>();
    private String ProfileDirectory;
    private ObjectOutputStream objectOutputStream = null;
    private ObjectInputStream objectInputStream = null;
    File usernamesDir= new File("Usernames.txt");

    Scanner scanner;

    //singletone class
    private DataBase() {
    }

    private static DataBase dataBase = new DataBase();

    public static DataBase getDataBase() {
        return dataBase;
    }

    public void initMap() {

        try {
            if (!new File("Usernames.txt").exists()) {
                usernamesDir.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Server.AllProfiles = new ConcurrentHashMap<>();
        try {
            Scanner scanner = new Scanner(usernamesDir);
            while (scanner.hasNextLine()) {
                String string = scanner.nextLine().trim();
                Username.add(string);
            }
            //read account
            for (String string : Username) {
                Server.AllUsernames.add(string);
                ProfileDirectory = string + File_SUFFIX;
                objectInputStream = new ObjectInputStream(new FileInputStream(ProfileDirectory));
                Account account = (Account) objectInputStream.readObject();
                Server.AllProfiles.put(string, account);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("initialization done");
    }

    //Save new Profile in new txt file
    public synchronized void SaveProfile(Account account) {
        try {
            FileWriter fileWriter=new FileWriter(usernamesDir,true);
            Formatter formatter = new Formatter(fileWriter);
            formatter.format(account.getUsername() + "\n");
            formatter.close();
            fileWriter.close();
            ProfileDirectory = account.getUsername() + File_SUFFIX;
            File file=new File(ProfileDirectory);
            file.createNewFile();
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(ProfileDirectory));
            objectOutputStream.writeObject(account);
            objectOutputStream.close();
            Server.AllUsernames.add(account.getUsername());
            Server.AllProfiles.put(account.getUsername(), account);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Rewrite account
    public synchronized void UpdateProfile(Account account) {
        ProfileDirectory = account.getUsername()+File_SUFFIX;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(ProfileDirectory));
            objectOutputStream.writeObject(account);
            objectOutputStream.close();
            Server.AllProfiles.replace(account.getUsername(), account);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
