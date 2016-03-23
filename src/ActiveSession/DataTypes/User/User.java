/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActiveSession.DataTypes.User;

import DataXMLParsers.JAXBParser;
import Main.ConsoleUI.Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Nekres
 */
@XmlRootElement(name = "user")
@XmlType(propOrder = {"uid","first_name","last_name","last_seen","online","sex","photo_max_orig"})
public class User {
    private static final String METHOD = "https://api.vk.com/method/users.get.xml?user_ids=";
    private String first_name;
    private String last_name;
    private String uid;
    private String online;
    private LastSeen last_seen;
    private String sex;
    private String photo_max_orig;
    

    public User() {
    }

    public String getUid() {
        return uid;
    }
    @XmlElement
    public void setUid(String uid) {
        this.uid = uid;
    }

    public User(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }
    @XmlElement
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    @XmlElement
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getOnline() {
        return online;
    }
    @XmlElement
    public void setOnline(String online) {
        this.online = online;
    }

    public LastSeen getLast_seen() {
        return last_seen;
    }
    @XmlElement
    public void setLast_seen(LastSeen last_seen) {
        this.last_seen = last_seen;
    }

    public String getSex() {
        return sex;
    }
    @XmlElement
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoto_max_orig() {
        return photo_max_orig;
    }
    @XmlElement
    public void setPhoto_max_orig(String photo_max_orig) {
        this.photo_max_orig = photo_max_orig;
    }
    
    
    public static User getInfo(String id,Account.AccessToken token)throws MalformedURLException,IOException,JAXBException{
        URL url = new URL(METHOD.concat(id).concat("&fields=online,last_seen,sex,photo_max_orig").concat("&access_token="+token.getAccess_token()));
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        JAXBParser parser = new JAXBParser();
        Response response = (Response)parser.getObject(Response.class, connection.getInputStream());
        return response.getUser();
    }

    @Override
    public String toString() {
        return "Имя:"+this.first_name+"\nФамилия:"+this.last_name+
                "\nБыл в сети:"+ Controller.setData(this.last_seen.getTime())+
                "\n" + (this.online.equals(Integer.toString(1)) ? "Онлайн" : "Оффлайн"+
                "\nПол:"+(this.sex.equals(Integer.toString(1)) ? "женский" : "мужской"));
        
    }
    
  
    
}
