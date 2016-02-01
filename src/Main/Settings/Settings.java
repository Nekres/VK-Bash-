/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Settings;

import ActiveSession.CurrentUser;
import Main.ConsoleUI.Controller;
import VkExceptions.BadParamsException;
import VkExceptions.WrongNameException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Nekres
 */
@XmlRootElement
@XmlType(propOrder = {"friend"})
public class Settings {
    private Map<String, String> short_names = new HashMap<>();
    public Map<String, String> getFriend() {
        return short_names;
    }
    @XmlElement
    public void setFriend(Map<String, String> short_names) {
        this.short_names = short_names;
    }
    public void add(String real, String shorten, CurrentUser user) throws BadParamsException{
        try{
        Controller.getIdByName(real, user,this);
        }catch(WrongNameException e){
            System.out.println("Неверное имя.");
        }
        this.short_names.put(shorten, real);
    }
    
    
    
    
}
