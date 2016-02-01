/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account;

import VkExceptions.BadParamsException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Nekres
 */
public class AccessToken {
    private String access_token;
    private int uid;
    private File token;

    public AccessToken(File token) throws Exception{
        this.access_token = takeToken(token);
        this.uid = getId(token);
        this.token = token;
    }
    public AccessToken(){}

    public int getUid() {
        return uid;
    }
    private String takeToken(File file)throws FileNotFoundException,IOException,BadParamsException{
            String token = read(file);
            token = token.substring(token.indexOf("access_token=")+13, token.indexOf("&"));
            return token;
    }
    private String read(File file) throws FileNotFoundException,IOException,BadParamsException{
            try{
            FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            String text = br.readLine();
            return text;
            }catch(FileNotFoundException e){
                throw new BadParamsException();
            }
            catch(IOException e){
                throw new BadParamsException();
            }
    }
    private int getId(File file)throws FileNotFoundException,IOException,BadParamsException{
      try{
      String uid = read(file);
      uid = uid.substring(uid.indexOf("id=")+3, uid.length());
      return Integer.valueOf(uid);
        }catch(FileNotFoundException e){
            System.out.println("Файл не найден.");
        }
      return 0;
    }
    public String getAccess_token(){
        return access_token;
    }
    
}
