package main.server;

import main.Common.Message.*;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientHandler implements Runnable {
    final private ObjectOutputStream OutPut;
    final private ObjectInputStream InPut;
    private AtomicInteger usersCount;
    private boolean IsOnline;

    public ClientHandler(Socket socket, AtomicInteger usersCount)
            throws IOException {
        this.OutPut = new ObjectOutputStream(socket.getOutputStream());
        this.InPut = new ObjectInputStream(socket.getInputStream());
        this.usersCount = usersCount;
        this.IsOnline = true;
    }

    public void run() {
        Message receivedMessage;
        AnswerMessage answerMessage = null;

        while (IsOnline) {
            try {
                receivedMessage = (Message) InPut.readObject();

                if (receivedMessage instanceof UserExistMessage) {
                    answerMessage = new AnswerMessage();
                    UserExistMessage userExistMessage = (UserExistMessage) receivedMessage;
                    answerMessage.setValue(userExistMessage.Handle(Server.AllProfiles));
                    OutPut.writeObject(answerMessage);
                } else if (receivedMessage instanceof LoginMessage) {
                    answerMessage = new AnswerMessage();
                    LoginMessage loginMessage = (LoginMessage) receivedMessage;
                    answerMessage.setValue(loginMessage.CheckPassword(Server.AllProfiles));
                    answerMessage.setProfile(loginMessage.Handle(Server.AllProfiles));
                    OutPut.writeObject(answerMessage);
                    System.out.println("[Login]" + "[" + loginMessage.getUsername() + "]");
                } else if (receivedMessage instanceof SignUpMessage) {
                    answerMessage = new AnswerMessage();
                    SignUpMessage signUpMessage = (SignUpMessage) receivedMessage;
                    signUpMessage.Handle(Server.AllProfiles);
                    answerMessage.setValue(true);
                    OutPut.writeObject(answerMessage);
                    DataBase.getDataBase().SaveProfile(signUpMessage.getProfile());
                    System.out.println("[" + signUpMessage.getProfile().getUsername() + "] rgister [account image addres(todo)]");
                } else if (receivedMessage instanceof LikeMessage) {
                    answerMessage = new AnswerMessage();
                    LikeMessage likeMessage = (LikeMessage) receivedMessage;
                    answerMessage.setValue(API.Like(likeMessage.post, likeMessage.WhoLiked));
                    OutPut.writeObject(answerMessage);
                } else if (receivedMessage instanceof NewPostMessage) {
                    answerMessage = new AnswerMessage();
                    NewPostMessage newPostMessage = (NewPostMessage) receivedMessage;
                    OutPut.writeObject(answerMessage);
                    API.NewPost(newPostMessage.getNewPost());

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
