/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;



/**
 *
 * @author Nekres
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    
    public static void main(String[] args)throws Exception{
        Oauth oauth = new Oauth();
        oauth = (oauth).new ApiBuilder().client_id("5088138")
                .display("mobile")
                .redirect_uri("https://oauth.vk.com/blank.html")
                .response_type("token")
                .scope("2")
                .v("5.37")
                .build();
        URL url = new URL(oauth.getRequest());
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("content-type", "text/plain;charset=utf-8 ");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
        System.out.println(getParams(connection));
        
    }
    private static String getParams(HttpURLConnection connection) throws IOException{
        String text,ip_h,lg_h,to,action;
        text = "";
        ip_h = "";
        lg_h = "";
        to = "";
        action = "";
        int index = 0;                                                                             //Здесь я пытаюсь получить все значения
        BufferedReader BR = new BufferedReader(new InputStreamReader(connection.getInputStream()));//из <form> и сформировать пост-запрос.
        FileWriter fw = new FileWriter(new File("text.txt"));
        Scanner scan = new Scanner(connection.getInputStream());
        while ((text = BR.readLine()) != null) {
        System.out.println(text);
        if (text.contains("action=\"https")){
            action =text.substring(text.indexOf("https"),text.indexOf("\">"));
        }
        if (text.contains("ip_h")){
            ip_h = text.substring(text.indexOf("value=\"")+7, text.indexOf("/>")-4);
        }
        if (text.contains("lg_h")){
            lg_h = text.substring(text.indexOf("value=\"")+7, text.indexOf("/>")-4);
        }
        if (text.contains("name=\"to\"")){
            to = text.substring(text.indexOf("value=\"")+7, text.indexOf("\">"));
        }
        }
        
       return action+"&ip_h"+ip_h+"&lg_h"+lg_h+"&to"+to+"&"; 
    }
}
    

