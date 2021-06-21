package main.Common.Message;
import main.Common.Account;
import java.util.Map;

public class GetMyAccountMessage implements Message{
    String Username;
    public GetMyAccountMessage(String username) {
        Username = username;
    }
    public Account Handle(Map<String, Account>map){
        return map.get(Username);
    }



}
