package main.Client;

import main.Common.Message.AnswerMessage;
import main.Common.Message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

public class ToServer {
    public static String serverAddress = "localhost";
    public static final int PORT = 8080;

    private static boolean isConnected = false;
    public static Socket socket;
    public static ObjectInputStream socketIn;
    public static ObjectOutputStream socketOut;

    public static boolean isConnected(){
        return isConnected;
    }

    public static Boolean connectToServer(){
        if(socket != null) return false;
        try{
            System.out.println("server ip : " + serverAddress);
            socket = new Socket( serverAddress, PORT);
            socketOut = new ObjectOutputStream( socket.getOutputStream() );
            socketIn = new ObjectInputStream( socket.getInputStream() );
            isConnected = true;
            return true;

        }catch (ConnectException e){
        }
        catch (IOException e) {
        }
        return false;
    }


    public static Boolean disconnectFromServer(){
        try{
            socketIn.close();
            socketOut.close();
            socket.close();
            isConnected = false;

            socket = null;
            socketIn = null;
            socketOut = null;

            return true;
        }
        catch (SocketException | NullPointerException e ){
        }
        catch( Exception e){
            e.printStackTrace();
        }
        socket = null;
        socketIn = null;
        socketOut = null;
        return false;
    }
    public static AnswerMessage sendToServer(Message message){
        try {
            socketOut.writeObject(message);
            return (AnswerMessage) socketIn.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
}
