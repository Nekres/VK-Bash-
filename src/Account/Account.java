/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * @author Nekres
 */
public class Account {
    private static final String method = "https://api.vk.com/method/METHOD_NAME?PARAMETERS&access_token=ACCESS_TOKEN";
    private final AccessToken token;
    private final int id;

    public Account(AccessToken token) {
        this.token = token;
        this.id = token.getUid();
    }
    
    public static String getMethod() {
        return method;
    }
    public void SendMessage(String id, String message) throws Exception{
            String uri = method.substring(0, method.indexOf("d/"))+"id"
            +"&message="+URLEncoder.encode(message,"utf-8")+"&access_token="+token;
            URL url = new URL(uri);
            makeRequest(url);
}
    private void makeRequest(URL url) throws Exception{
            HttpURLConnection connection;
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String text;
            while ((text = bf.readLine()) != null){
            System.out.println(text);  
    }
}

    public int getId() {
        return id;
    }
    
}
