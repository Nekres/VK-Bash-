/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActiveSession.DataTypes;

import java.net.URL;
import Account.AccessToken;
import DataXMLParsers.JAXBParser;
import VkExceptions.BadParamsException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Nekres
 */
    @XmlRootElement(name = "user")
    @XmlType(propOrder = {"uid","first_name","last_name","domain","city","online","user_id"})
    public class Friend{
    private static final String FRIEND_GET = "https://api.vk.com/method/friends.get.xml?";
    private int uid;
    private String first_name;
    private String last_name;
    private String domain;
    private String city;
    private int online;
    private int user_id;
    
    public static List<Friend> get(int user_id,String order,int count,int offset,String fields,String name_case,AccessToken token)
    throws MalformedURLException,IOException,JAXBException,BadParamsException{
        URL url = new URL(FRIEND_GET+"user_id="+user_id+"&order=name&count=50&offset=0"
        + "&fields=city,domain&name_case=nom&access_token="+token.getAccess_token());
        JAXBParser parser = new JAXBParser();
        XMLResponse friendParser;
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        friendParser = (XMLResponse)parser.getObject(XMLResponse.class,connection.getInputStream());
        if (friendParser.getUser() == null){
            throw new BadParamsException();
        }
        return friendParser.getUser();
    }
    @XmlElement
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    @XmlElement
    public void setUid(int uid) {
        this.uid = uid;
    }
    @XmlElement
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    @XmlElement
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    @XmlElement
    public void setDomain(String domain) {
        this.domain = domain;
    }
    @XmlElement
    public void setCity(String city) {
        this.city = city;
    }
    @XmlElement
    public void setOnline(int online) {
        this.online = online;
    }
    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getDomain() {
        return domain;
    }

    public String getCity() {
        return city;
    }

    public int getOnline() {
        return online;
    }

    public int getUser_id() {
        return user_id;
    }

    @Override
    public String toString() {
        return "Friend{" + "uid=" + uid + ", first_name=" + first_name + ", last_name=" + last_name + ", domain=" + domain + ", city=" + city + ", online=" + online + ", user_id=" + user_id + '}';
    }
    
    
        
    }
