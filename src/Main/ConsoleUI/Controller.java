/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.ConsoleUI;

import Account.AccessToken;
import ActiveSession.DataTypes.Friend;
import ActiveSession.DataTypes.Message;
import ActiveSession.CurrentUser;
import VkExceptions.BadParamsException;
import VkExceptions.WrongNameException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Nekres
 */
public class Controller {
    private String help;
    
    
    public static void printWelcomeMessage(String message,CurrentUser user){
        int dots;
        dots =  message.length();
        printDots(dots);
        System.out.println("\n"+message);
        printDots(dots);
        System.out.println("\n"+user.getName()+"|Сообщения["+user.getUnreadMessages()+"]|");
        printDots(dots);
        System.out.println();
    }
    private static void printDots(int string1){
        for (int i=0; i < string1;i++)
        System.out.print(".");
    }
    public static void printFriendsOnline(List<Friend> friends){
        int i = 0;
        int strSize = 60;
        int minStrSize = 60;
        int check = 0;
        String online1 = "";
        try{
        while(i < friends.size()){
            if (friends.get(i).getOnline() == 1){
                online1 = online1+friends.get(i).getFirst_name()+" "+friends.get(i).getLast_name()+"|";
                check = online1.length();
            }
            if (check > strSize){
                online1 = online1+"\n";
                strSize = strSize + minStrSize;
            }
            i++;
        }
        System.out.println(online1);
        }catch(NullPointerException e){
            System.out.println("Нет друзей онлайн.");
        }
    }
    public static void getInfo(AccessToken token)throws MalformedURLException,IOException,JAXBException{
     //   getIdByName(null, null)
    }
    public static void getUnread(CurrentUser user,AccessToken token)throws IOException,JAXBException,BadParamsException{
        try{
        int count = 0;
        List<Message> message = Message.get(0, 0, 100, 0, 1, 0, 0,token);
        String name;
        count = message.size()-1;
        while(count >= 0){
            for(int i = 0;i < user.getFriends().size();i++){
                if (user.getFriends().get(i).getUser_id() ==message.get(count).getUid()){
                name = user.getFriends().get(i).getFirst_name();
                System.out.println(name+":"+message.get(count).getBody()+"\n"+setData(message.get(count).getDate()));
                }
        }
        count--;
        }
        }catch(BadParamsException e){
            System.out.println("Новых сообщений нет.");
        }
    }
    public static void printFriends(CurrentUser user){
        int strSize = 60;
        int minStrSize = 60;
        String friends = "";
        try{
            for (int i = 0; i < user.getFriends().size(); i++) {
                friends = friends + user.getFriends().get(i).getFirst_name()+" "+user.getFriends().get(i).getLast_name()+"|";
                if (friends.length() > strSize){
                    strSize = strSize+minStrSize;
                    friends = friends+"\n";
                }
            }
            System.out.println(friends);
         }catch(NullPointerException e){
            System.out.println("У тебя нет друзей.");
        }
    }
    public static void returnDialog(CurrentUser user,AccessToken token)throws BadParamsException,MalformedURLException,IOException,JAXBException{
        String name,last_message;
        Scanner scan = new Scanner(System.in,"866");
        System.out.print("Имя:");
        name = scan.nextLine();
        System.out.print("Кол-во:");
        try{
        int id = getIdByName(name, user);
        List<Message> message = Message.getHistory(scan.nextInt(), id, token);
            for(int i = message.size()-1;i != -1;i--){
                if (Integer.valueOf(message.get(i).getFrom_id()) == user.getId()){
                System.out.println("Вы: "+message.get(i).getBody()+"\n"+setData(message.get(i).getDate()));
                last_message = message.get(i).getBody();
                }
                else{
                String first_name = name.substring(0, name.indexOf(" "));
                System.out.println(first_name+": "+message.get(i).getBody()+"\n"+setData(message.get(i).getDate()));
                last_message = message.get(i).getBody();
                }
            }
             }catch(BadParamsException e){
            System.out.println("Неверное имя");
        }
    }
    private static String setData(int a){
            long dateTime = a;
            Date date = new Date(dateTime*1000L);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
            String formattedDate = dateFormat.format(date);
            return formattedDate;
    }
    private static int getIdByName(String name,CurrentUser user)throws BadParamsException{
        String first_name = name.substring(0, name.indexOf(" "));
        String last_name = name.substring(name.indexOf(" ")+1,name.length());
        List<Friend> friends = user.getFriends();
        int i = 0;
        int id = 0;
        while(i < friends.size()){
            if ((friends.get(i).getFirst_name().trim().toLowerCase().equals(first_name.trim().toLowerCase()))
               && (friends.get(i).getLast_name().trim().toLowerCase().equals(last_name.trim().toLowerCase()))){
               id = friends.get(i).getUser_id();
            }
            i++;
        }
        if (id == 0){
            throw new WrongNameException();
        }
        return id;
    }
    public static void sendMessage(CurrentUser user, AccessToken token)throws UnsupportedEncodingException,MalformedURLException,IOException,BadParamsException{
        String body;
        int id;
        Scanner scan = new Scanner(System.in,"866");
        System.out.print("Имя:");
        try{
        id = getIdByName(scan.nextLine(), user);
        System.out.println("Сообщение:");
        body = scan.nextLine();
        Message.send(id,body, token);
        System.out.println("Отправлено.");
        }catch(BadParamsException e){
            System.out.println("Неверное имя.");
        }
    }
    
    
}
