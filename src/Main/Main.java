/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Account.AccessToken;
import Account.Account;
import ActiveSession.User;
import java.io.File;

/**
 *
 * @author Nekres
 */
public class Main{

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
            File f = new File("A:\\AccessTokens.txt");
            AccessToken token = new AccessToken(f);
            Account acc = new Account(token);
            User user = new User(acc);
            System.out.println(user);
         
    }            
}
    

  


    

