/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Account.AccessToken;
import ActiveSession.DataTypes.Friend;
import ActiveSession.CurrentUser;
import DataXMLParsers.JAXBParser;
import Main.ConsoleUI.Controller;
import Main.Settings.Settings;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import javax.xml.bind.UnmarshalException;

/**
 *
 * @author Nekres
 */
public class Main{
    private static final String PATH = "settings.xml";
    private static final String HELP = "\nall - просмотреть всех друзей\nonline - просмотреть всех друзей онлайн"+
    "\ncut -задать короткое имя(в дальнейшем обращаться к нему так)\nshort - показать короткие имена друзей"
    + "\nsend - написать сообщение\nhistory -открыть историю переписки с другом \nunread - показать все непрочитанные сообщения"+
    "\nshow - показать определенное кол-во песен\nshow all - показать все песни"
    +"\ndownload arg1 - где \"arg1\" номер песни в списке аудиозаписей"+
    "\nclose - закрыть приложение\n Если вы хотите прервать отправку сообщения, введите в тело break"+
            "\n download all - скачать все песни.";

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
            AccessToken token = new AccessToken();
            Scanner scan = new Scanner(System.in,"866");
            try{
            token = new AccessToken(new File(args[0]));
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Укажите правильный путь к токену. Например disk:\\someFolder\\token.txt\nPress enter to exit.");
                scan.nextLine();
                System.exit(0);
            }
            URL stat = new URL("https://api.vk.com/method/stats.trackVisitor?access_token=" + token.getAccess_token());
            BufferedReader reader = new BufferedReader(new InputStreamReader(stat.openStream()));
            reader.readLine();
            CurrentUser user = new CurrentUser(token);
            System.out.println("Raskolnikov v1.0 launched.");
            Controller.printWelcomeMessage("Добро пожаловать в чат! Он работает как командная строка. Список команд -help :",user);
            Settings settings = new Settings();
            JAXBParser parser = new JAXBParser();
            try{
                settings = (Settings)parser.getObjectFromFile(new File(PATH), Settings.class);
            }catch(UnmarshalException e){
                parser.saveObjectToFile(new File(PATH), settings);
            }
            parser.saveObjectToFile(new File(PATH), settings);
            String choice;
            boolean notClose = true;
            while(notClose){
            choice = scan.nextLine().trim().toLowerCase();
            switch(choice){
                case "unread":Controller.getUnread(user, token);
                    break;
                case "history":Controller.returnDialog(user, token, settings);
                    break;
                case "all":Controller.printFriends(user);
                    break;
                case "online":Controller.printFriendsOnline(user);
                    break;
                case "help":System.out.println(HELP);
                    break;
                case "close":notClose = false;
                    break;
                case "send":Controller.sendMessage(user, token,settings);
                   break;
                case "download":Controller.download(user, settings, token);
                   break;
                case "show":Controller.showMusic(user, token);
                   break;
                case "show all":Controller.printTo(600000, user, token);
                   break;
                case "cut":Controller.makeShorter(user, settings,PATH);
                   break;
                case "short":Controller.printShorten(settings);
                   break;
                case "info":Controller.getInfo(user, token, settings);
                    break;
                case "download all":Controller.downloadAll(user, settings, token);
                   break;   
                default:System.out.println("Неверная команда.");
            }
            }
    }
        
            
}
    

  
 

    

