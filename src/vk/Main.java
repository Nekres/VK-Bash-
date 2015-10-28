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
                System.out.println(oauth.doOauth());
    }

}
    

