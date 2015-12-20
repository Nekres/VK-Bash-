/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActiveSession;

import Account.Account;
import ResponseHandler.ResponseHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Nekres
 */
public class User  {
    private final int id;
    private final String name;
    private final Account account;
  //  private final Friends friends;
    

    public User(Account account) throws IOException{
        this.account = account;
        this.name = takeUserName();
        this.id = account.getId();
    }
    private String takeUserName () throws IOException{
        String user;
        user = invoke(new URL("https://api.vk.com/method/users.get?user_ids=172321388&name_case=nom"));
        user = user.substring(user.indexOf("first_name")+13, user.indexOf("hidden")-3);
        user = user.replace("\",\"last_name\":\"", " ");
        return user;
    }
    private String invoke(URL url) throws IOException{
        String text = "";
        String info = "";
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        while ((text = reader.readLine()) != null){
            info = text;
        }
        return info;
    }

    @Override
    public String toString() {
        return "user{ \nname:"+name+"\nid:"+id+"\n}";
    }

    public String getName() {
        return name;
    }
    public int getId(){
        return id;
    }
    
    
    
}
