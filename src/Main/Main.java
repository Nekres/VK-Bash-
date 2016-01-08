/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Account.AccessToken;
import ActiveSession.DataTypes.Friend;
import ActiveSession.User;
import Main.ConsoleUI.Controller;
import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nekres
 */
public class Main{
    private static final String HELP = "-f all - просмотреть всех друзей\n-f online - просмотреть всех друзей онлайн\n"
    + "-m send - написать сообщение\n-p close - закрыть приложение\n-p refresh - обновить\n-m history -открыть историю переписки с другом";

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
            AccessToken token = new AccessToken(new File("A:\\accessTokens.txt"));
            User user = new User(token);
            System.out.println("Raskolnikov v1.0 launched.");
            Controller.printWelcomeMessage("Добро пожаловать в чат! Он работает как командная строка. Список команд -help :",user);
            List<Friend> friends = Friend.get(user.getId(),"name",100,0,"city,domain","nom",token);
            Scanner scan = new Scanner(System.in,"866");
            String choice;
            boolean close = true;
            while(close){
            choice = scan.nextLine().trim();
            switch(choice){
                case "-f online":Controller.printFriendsOnline(friends);
                    break;
                case "-help":System.out.println(HELP);
                    break;
                case "-p close":close = false;
                    break;
                case "-m send":Controller.sendMessage(user, token);
                   break;
                default:System.out.println("Неверная команда.");
            }
            }
    }
        
            
}
    

  
 

    

