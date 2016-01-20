/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Account.AccessToken;
import ActiveSession.DataTypes.Friend;
import ActiveSession.CurrentUser;
import ActiveSession.DataTypes.Audio;
import Main.ConsoleUI.Controller;
import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nekres
 */
public class Main{
    private static final String HELP = "\n-f all - просмотреть всех друзей\n-f online - просмотреть всех друзей онлайн\n-f info - подробная информация о друге"+
    "\n-f setshort -задать короткое имя(в дальнейшем обращаться к нему так)"
    + "\n-m send - написать сообщение\n-m history -открыть историю переписки с другом \n-m unread - показать все непрочитанные сообщения"+
    "\n-a show - список всех песен"
    +"\n-a download arg1 - где \"arg1\" номер песни в списке аудиозаписей\n-a path - установить папку для загрузки аудиофайлов\n"+
    "-p path - изменить путь для хранения настроек\n-p close - закрыть приложение\n-p refresh - обновить";

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
            AccessToken token = new AccessToken(new File("A:\\accessTokens.txt"));
            CurrentUser user = new CurrentUser(token);
            System.out.println("Raskolnikov v1.0 launched.");
            Controller.printWelcomeMessage("Добро пожаловать в чат! Он работает как командная строка. Список команд -help :",user);
            List<Friend> friends = Friend.get(user.getId(),"name",100,0,"city,domain,online","nom",token);
            Scanner scan = new Scanner(System.in,"866");
//            URL url = new URL("http://cs7-5v4.vk-cdn.net/p7/23856b781597d8.mp3?extra=SwZQ_uHO8D56HcoK_BgXQJ0KkeCDhEZlLlHLRfg2t_NnpvWCfzeF593318U8I-kRjweJfr3LFhDUXTN_Dq_u2cvGrKTOpyZi");
//            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
//            FileOutputStream file = new FileOutputStream(new File("some.mp3"));
//            file.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
          //  Audio.get(user.getId(), token);
            String choice;
            boolean notClose = true;
            while(notClose){
            choice = scan.nextLine().trim().toLowerCase();
            switch(choice){
                case "-m unread":Controller.getUnread(user, token);
                    break;
                case "-m history":Controller.returnDialog(user, token);
                    break;
                case "-f all":Controller.printFriends(user);
                    break;
                case "-f online":Controller.printFriendsOnline(friends);
                    break;
                case "-help":System.out.println(HELP);
                    break;
                case "-p close":notClose = false;
                    break;
                case "-m send":Controller.sendMessage(user, token);
                   break;
                default:System.out.println("Неверная команда.");
            }
            }
    }
        
            
}
    

  
 

    

