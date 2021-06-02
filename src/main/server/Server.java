package main.server;

import main.Common.Profile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    public static int port;
    public static Vector<String> AllUsernames;
    public static Map<String, Profile> AllProfiles;
    public static AtomicInteger usersCount;

    public static void main(String[] args) {
        DataBase.getDataBase().initMap();
        ServerSocket serverSocket=null;
        try {
           serverSocket=new ServerSocket(8080);
        } catch (IOException e) {
            System.out.println("serverSocket connection faild");
        }
        while (true){
            try {
                Socket ClientSocket=serverSocket.accept();
                ClientHandler clientHandler=new ClientHandler(ClientSocket,usersCount);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                System.out.println("main.Client Connection faild");
                e.printStackTrace();
            }
        }
    }
}