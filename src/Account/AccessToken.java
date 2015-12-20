/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account;

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
    private final String access_token;
    private final String expires_in;
    private final int uid;
    private final File token;

    public AccessToken(File token) throws Exception{
        this.access_token = takeToken(token);
        this.expires_in = takeExpireTime(token);
        this.uid = getId(token);
        this.token = token;
    }

    public int getUid() {
        return uid;
    }
    private String takeToken(File file)throws FileNotFoundException,IOException{
            String token = read(file);
            token = token.substring(token.indexOf("access_token=")+13, token.indexOf("&"));
            return token;
    }
    private String takeExpireTime(File file){
     return null;   
    }
    private String read(File file) throws FileNotFoundException,IOException{
        FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            String text = br.readLine();
            return text;
    }
    private int getId(File file)throws FileNotFoundException,IOException{
      String uid = read(file);
      uid = uid.substring(uid.indexOf("id=")+3, uid.length()-1);
      return Integer.valueOf(uid);
    }
    
}