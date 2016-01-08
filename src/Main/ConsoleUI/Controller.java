/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.ConsoleUI;

import Account.AccessToken;
import ActiveSession.DataTypes.Friend;
import ActiveSession.DataTypes.Message;
import ActiveSession.User;
import VkExceptions.BadParamsException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nekres
 */
public class Controller {
    private String help;
    
    
    public static void printWelcomeMessage(String message,User user){
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
        String online = "";
        while(i < friends.size()){
            if (friends.get(i).getOnline() == 1){
                online = online+friends.get(i).getFirst_name()+" "+friends.get(i).getLast_name()+"|";
                check = online.length();
            }
            if (check > strSize){
                online = online+"\n";
                strSize = strSize + minStrSize;
            }
            i++;
        }
        System.out.println(online);
    }
    private static int getIdByName(String name,User user)throws BadParamsException{
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
            throw new BadParamsException();
        }
        return id;
    }
    public static void sendMessage(User user, AccessToken token)throws UnsupportedEncodingException,MalformedURLException,IOException,BadParamsException{
        String body;
        int id;
        Scanner scan = new Scanner(System.in,"866");
        System.out.print("Получатель:");
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
