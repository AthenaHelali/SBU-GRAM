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
        this.IsOnline=true;
    }

    public void run() {
         Message receivedMessage;
        AnswerMessage answerMessage;

          while (IsOnline){
              try {
                  receivedMessage=(Message) InPut.readObject();

                  if(receivedMessage instanceof UserExistMessage) {
                      answerMessage=new AnswerMessage();
                     UserExistMessage userExistMessage= (UserExistMessage)receivedMessage;
                      answerMessage.setValue(userExistMessage.Handle(Server.AllProfiles));
                  }

                  else if(receivedMessage instanceof LoginMessage){
                      answerMessage=new AnswerMessage();
                      LoginMessage loginMessage=(LoginMessage) receivedMessage;
                      answerMessage.setValue(loginMessage.Handle(Server.AllProfiles));
                  }
                  else if(receivedMessage instanceof SignUpMessage){
                      answerMessage=new AnswerMessage();
                      SignUpMessage signUpMessage=(SignUpMessage) receivedMessage;
                      signUpMessage.Handle(Server.AllProfiles);
                      DataBase.getDataBase().SaveProfile(signUpMessage.getProfile());
                  }
                  else if(receivedMessage instanceof LikeMessage){
                      answerMessage=new AnswerMessage();
                      LikeMessage likeMessage=(LikeMessage) receivedMessage;
                      answerMessage.setValue(API.Like(likeMessage.post, likeMessage.WhoLiked));
                  }
              } catch (IOException e) {
                  e.printStackTrace();
              } catch (ClassNotFoundException e) {
                  e.printStackTrace();
              }

          }
    }
}
