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
public class Oauth {
    private String request = "https://oauth.vk.com/authorize?"+
    "client_id=APP_ID&"+
    "scope=PERMISSIONS&"+ 
    "redirect_uri=REDIRECT_URI&"+
    "display=DISPLAY&"+
    "v=API_VERSION&"+
    "response_type=token";      
    
    private Oauth (final ApiBuilder ab){
        this.request = request.replace("APP_ID",ab.getClient_id())
                .replace("PERMISSIONS", ab.getScope())
                .replace("REDIRECT_URI", ab.getRedirect_uri())
                .replace("DISPLAY", ab.getDisplay())
                .replace("API_VERSION", ab.getV())
                .replace("token", ab.getResponse_type());
    }
    public Oauth(){
    }
   
    public String getRequest(){
        return request;
    }
    public String doOauth() throws IOException{
        URL url = new URL(this.getRequest());
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("content-type", "text/plain;charset=utf-8 ");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
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
    
    public class ApiBuilder{
        
       private String client_id;
       private String scope;
       private String redirect_uri;
       private String display;
       private String v;
       private String response_type;
        
        public ApiBuilder client_id(final String client_id){
            this.client_id = client_id;
            return this;
        }
        public ApiBuilder scope(final String scope){
            this.scope = scope;
            return this;
        }
        public ApiBuilder redirect_uri(final String redirect_uri){
            this.redirect_uri = redirect_uri;
            return this;
        }
        public ApiBuilder display(final String display){
            this.display = display;
            return this;
        }
        public ApiBuilder v(final String v){
            this.v = v;
            return this;
        }
        public ApiBuilder response_type(final String response_type){
            this.response_type = response_type;
            return this;
        }
        

        public String getClient_id() {
            return client_id;
        }

        public String getScope() {
            return scope;
        }

        public String getRedirect_uri() {
            return redirect_uri;
        }

        public String getDisplay() {
            return display;
        }

        public String getV() {
            return v;
        }

        public String getResponse_type() {
            return response_type;
        }
        public Oauth build(){
            return new Oauth(this);
        }
        
        
        
    }
    
    
    }

    

