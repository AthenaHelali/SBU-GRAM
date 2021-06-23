package main.server;

import main.Common.Account;
import main.Common.Message.*;
import main.Common.Post;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
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
                    OutPut.reset();
                } else if (receivedMessage instanceof LoginMessage) {
                    answerMessage = new AnswerMessage();
                    LoginMessage loginMessage = (LoginMessage) receivedMessage;
                    answerMessage.setValue(loginMessage.CheckPassword(Server.AllProfiles));
                    answerMessage.setAccount(loginMessage.Handle(Server.AllProfiles));
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();
                    System.out.println("[Login]" + "[" + loginMessage.getUsername() + "]");
                } else if (receivedMessage instanceof SignUpMessage) {
                    answerMessage = new AnswerMessage();
                    SignUpMessage signUpMessage = (SignUpMessage) receivedMessage;
                    signUpMessage.Handle(Server.AllProfiles);
                    answerMessage.setValue(true);
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();
                    DataBase.getDataBase().SaveProfile(signUpMessage.getProfile());
                    System.out.println("[" + signUpMessage.getProfile().getUsername() + "] rgister [account image addres(todo)]");
                } else if (receivedMessage instanceof LikeMessage) {
                    answerMessage = new AnswerMessage();
                    LikeMessage likeMessage = (LikeMessage) receivedMessage;
                    answerMessage.setValue(API.Like(likeMessage.post, Server.AllProfiles.get(likeMessage.WhoLiked)));
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();
                } else if (receivedMessage instanceof NewPostMessage) {
                    answerMessage = new AnswerMessage();
                    NewPostMessage newPostMessage = (NewPostMessage) receivedMessage;
                    Server.AllProfiles.get(newPostMessage.getNewPost().getWriterUsername()).NewPost(newPostMessage.getNewPost());
                    DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(newPostMessage.getNewPost().getWriterUsername()));
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();
                } else if (receivedMessage instanceof LogOutMessage) {
                    IsOnline = false;
                    answerMessage = new AnswerMessage();
                    answerMessage.setValue(true);
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();
                } else if (receivedMessage instanceof UpdateProfileMessage) {
                    answerMessage = new AnswerMessage();
                    UpdateProfileMessage updateProfileMessage = (UpdateProfileMessage) receivedMessage;
                    updateProfileMessage.Handle(Server.AllProfiles);
                    DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(updateProfileMessage.getAccount().getUsername()));
                    answerMessage.setValue(true);
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();
                } else if (receivedMessage instanceof GetAllProfilesMessage) {
                    answerMessage = new AnswerMessage();
                    GetAllProfilesMessage getAllProfilesMessage = (GetAllProfilesMessage) receivedMessage;
                    answerMessage.setOthersAccounts(getAllProfilesMessage.Handle(new ArrayList<Account>(Server.AllProfiles.values())));
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();
                } else if (receivedMessage instanceof FollowMessage) {
                    answerMessage = new AnswerMessage();
                    FollowMessage followMessage = (FollowMessage) receivedMessage;
                    API.Follow(followMessage.getFollower(), followMessage.getFollowedUser());
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();
                } else if (receivedMessage instanceof timelinePostsMessage) {
                    answerMessage = new AnswerMessage();
                    timelinePostsMessage timelinePostsMessage = (timelinePostsMessage) receivedMessage;
                    answerMessage.setPosts(timelinePostsMessage.Handle(Server.AllProfiles));
                    ArrayList<Post> posts = answerMessage.getPosts();
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();
                } else if (receivedMessage instanceof GetProfileImageMessage) {
                    answerMessage = new AnswerMessage();
                    answerMessage.setProfileImage(Server.AllProfiles.get(((GetProfileImageMessage) receivedMessage).getUsername()).getProfileImage());
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();
                } else if (receivedMessage instanceof getCommentsMessage) {
                    answerMessage = new AnswerMessage();
                    getCommentsMessage commentsMessage = (getCommentsMessage) receivedMessage;
                    answerMessage.setComments(commentsMessage.Handle(Server.AllProfiles));
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();

                } else if (receivedMessage instanceof NewCommentMessage) {
                    answerMessage = new AnswerMessage();
                    NewCommentMessage newCommentMessage = (NewCommentMessage) receivedMessage;
                    newCommentMessage.Handle(Server.AllProfiles);
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();
                    DataBase.getDataBase().UpdateProfile(Server.AllProfiles.get(newCommentMessage.getPostWriter()));
                } else if (receivedMessage instanceof getAccountbyeUsernameMessage) {
                    answerMessage = new AnswerMessage();
                    getAccountbyeUsernameMessage accountbyeUsernameMessage = (getAccountbyeUsernameMessage) receivedMessage;
                    answerMessage.setOtherAccount(accountbyeUsernameMessage.Handle(Server.AllProfiles));
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();

                } else if (receivedMessage instanceof MyPostsMessage) {
                    answerMessage = new AnswerMessage();
                    MyPostsMessage myPostsMessage = (MyPostsMessage) receivedMessage;
                    answerMessage.setPosts(Server.AllProfiles.get(myPostsMessage.getUsername()).getMyPosts());
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();

                } else if (receivedMessage instanceof GetMyAccountMessage) {
                    answerMessage = new AnswerMessage();
                    GetMyAccountMessage getMyAccountMessage = (GetMyAccountMessage) receivedMessage;
                    answerMessage.setAccount(getMyAccountMessage.Handle(Server.AllProfiles));
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();

                } else if (receivedMessage instanceof UnfollowMessage) {
                    answerMessage = new AnswerMessage();
                    UnfollowMessage unfollowMessage = (UnfollowMessage) receivedMessage;
                    API.UnFollow(unfollowMessage.getFollower(), unfollowMessage.getUnfollowedUser());
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();

                } else if (receivedMessage instanceof forgetPasswordMessage) {
                    answerMessage = new AnswerMessage();
                    forgetPasswordMessage passwordMessage = (forgetPasswordMessage) receivedMessage;
                    answerMessage.setValue(passwordMessage.Handle(Server.AllProfiles));
                    if (answerMessage.getValue())
                        answerMessage.setPassword(Server.AllProfiles.get(passwordMessage.getUsername()).getPassword());
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();

                } else if (receivedMessage instanceof getFollowersNumberMessage) {
                    answerMessage = new AnswerMessage();
                    getFollowersNumberMessage followersNumberMessage = (getFollowersNumberMessage) receivedMessage;
                    answerMessage.setFollowers(Server.AllProfiles.get(followersNumberMessage.getUsername()).getFollowers().size());
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();

                } else if (receivedMessage instanceof getCommentNumberMessage) {
                    answerMessage = new AnswerMessage();
                    answerMessage.setCommentNumber(((getCommentNumberMessage) receivedMessage).Handle(Server.AllProfiles));
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();
                } else if (receivedMessage instanceof getLikesMessage) {
                    answerMessage = new AnswerMessage();
                    answerMessage.setLikesNumber(((getLikesMessage) receivedMessage).Handle(Server.AllProfiles));
                    OutPut.writeObject(answerMessage);
                    OutPut.reset();
                }else if(receivedMessage instanceof GetRepostNumberMessage){
                    answerMessage=new AnswerMessage();
                    answerMessage.setRepostNum(((GetRepostNumberMessage) receivedMessage).Handle(Server.AllProfiles));
                    OutPut.writeObject(answerMessage);
                }else if(receivedMessage instanceof repostMessage){
                    answerMessage=new AnswerMessage();
                    ((repostMessage)receivedMessage).Handle(Server.AllProfiles);
                    OutPut.writeObject(answerMessage);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
