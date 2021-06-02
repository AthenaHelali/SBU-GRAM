package server;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientHandler implements Runnable {
    final private ObjectOutputStream Out;
    final private ObjectInputStream In;
    private AtomicInteger usersCount;
    private boolean IsOnline;

    public ClientHandler(Socket socket, AtomicInteger usersCount)
            throws IOException {
        this.Out = new ObjectOutputStream(socket.getOutputStream());
        this.In = new ObjectInputStream(socket.getInputStream());
        this.usersCount = usersCount;
        this.IsOnline=true;
    }

    public void run() {
          while (IsOnline){
              //TODO
          }
    }
}
