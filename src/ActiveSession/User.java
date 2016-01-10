/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActiveSession;

import Account.AccessToken;
import ActiveSession.DataTypes.Friend;
import ActiveSession.DataTypes.Message;
import ActiveSession.DataTypes.XMLResponse;
import DataXMLParsers.JAXBParser;
import VkExceptions.BadParamsException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Nekres
 */

public class User  {
    private static final String METHOD = "https://api.vk.com/method/";
    private final int id;
    private final String name;
    private final AccessToken token;
    private final int unreadMessages;
    private Message messages;
    private List<Friend> friends;

    public User(AccessToken token) throws Exception{
        this.token = token;
        this.id = token.getUid();
        this.name = takeUserName();
        this.friends = Friend.get(token.getUid(),"name",50,0,"city,domain","nom",token);
        this.unreadMessages = takeUnreadMessages();
    }
    
    private String takeUserName () throws IOException,InterruptedException{
        String user;
        user = invokeJSON(new URL(METHOD+"users.get?user_ids="+id+"&name_case=nom"));
        user = user.substring(user.indexOf("first_name")+13, user.indexOf("hidden")-3);
        user = user.replace("\",\"last_name\":\"", " ");
        return user;
    }
    private String invokeJSON(URL url) throws IOException,InterruptedException{
        String text;
        String info = "";
        BufferedReader reader = connect(url);
        while ((text = reader.readLine()) != null){
            info = text;
        }
        return info;
    }
    private int takeUnreadMessages() throws MalformedURLException,IOException,InterruptedException,JAXBException,BadParamsException{
        JAXBParser parser = new JAXBParser();
        int i = 0;
        int count = 0;
        try{
        List<Message> message = Message.get(0, 0, 100, 0, 1, 0, 0,token);
        while ( i < message.size() ){
            if (message.get(i).getRead_state() == 0){
            count++;   
            }
            i++;
        }
        }catch(BadParamsException e){
            count = 0;
        }
          return count;
    }
    public static BufferedReader connect(URL url) throws IOException,ConnectException,InterruptedException{
     HttpURLConnection connection = (HttpURLConnection)url.openConnection();
     connection.setConnectTimeout(15000);
     BufferedReader reader;
     try{
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
     }catch(SocketTimeoutException e){
        connection.disconnect();
        connection.connect();
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
     }
     return reader;
    }
    public List<Friend> takeFriend() throws IOException,JAXBException{
        return friends;
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
    public List<Friend> getUser() {
        return friends;
    }
    public int getUnreadMessages(){
        return unreadMessages;
    }
    public List<Friend> getFriends() {
        return friends;
    }
    public void setUser(List<Friend> user) {
        this.friends = user;
    }
    
    
    
    
    
}
