/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Account.AccessToken;
import ActiveSession.DataTypes.Friend;
import ActiveSession.CurrentUser;
import ActiveSession.DataTypes.Message;
import DataXMLParsers.JAXBParser;
import Main.ConsoleUI.Controller;
import Main.Settings.Settings;
import VkExceptions.BadParamsException;
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
    private static final String HELP = "\n-f all - просмотреть всех друзей\n-f online - просмотреть всех друзей онлайн\n-f info - подробная информация о друге"+
    "\n-f cut -задать короткое имя(в дальнейшем обращаться к нему так)\n-f short - показать короткие имена друзей"
    + "\n-m send - написать сообщение\n-m history -открыть историю переписки с другом \n-m unread - показать все непрочитанные сообщения"+
    "\n-a show - показать определенное кол-во песен\n-a show all - показать все песни"
    +"\n-a download arg1 - где \"arg1\" номер песни в списке аудиозаписей"+
    "\n-p close - закрыть приложение\n-p refresh - обновить";

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
            List<Friend> friends = Friend.get(user.getId(),"name",100,0,"city,domain,online","nom",token);
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
                case "-m unread":Controller.getUnread(user, token);
                    break;
                case "-m history":Controller.returnDialog(user, token, settings);
                    break;
                case "-f all":Controller.printFriends(user);
                    break;
                case "-f online":Controller.printFriendsOnline(friends);
                    break;
                case "-help":System.out.println(HELP);
                    break;
                case "-p close":notClose = false;
                    break;
                case "-m send":Controller.sendMessage(user, token,settings);
                   break;
                case "-a download":Controller.download(user, settings, token);
                   break;
                case "-a show":Controller.showMusic(user, token);
                   break;
                case "-a show all":Controller.printTo(600000, user, token);
                   break;
                case "-f cut":Controller.makeShorter(user, settings,PATH);
                   break;
                case "-f short":Controller.printShorten(settings);
                   break;
                default:System.out.println("Неверная команда.");
            }
            }
    }
        
            
}
    

  
 

    

