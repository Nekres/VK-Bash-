/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActiveSession.DataTypes;

import Account.AccessToken;
import DataXMLParsers.JAXBParser;
import VkExceptions.BadParamsException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import javafx.scene.layout.BorderStroke;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Nekres
 */
@XmlRootElement(name = "message")
@XmlType(propOrder = {"mid","date","out","uid","read_state","title","body"})
public class Message {
    private static final String MESSAGE_GET = "https://api.vk.com/method/messages.get.xml?";
    private static final String MESSAGE_SEND = "https://api.vk.com/method/messages.send.xml?";
    private int mid;
    private int uid;
    private String date;
    private int read_state;
    private int out;
    private String title;
    private String body;
    public static XMLResponse response;

    public static List<Message> get(int out, int offset, int count, int time_offset,
        int filters,int preview_length, int last_message_id,AccessToken token) throws MalformedURLException,IOException,JAXBException,BadParamsException{
        URL url = new URL(MESSAGE_GET+"out="+out+"&offset="+offset+"&count="
        +count+"&time_offset="+time_offset+"&filters="+filters+"&preview_length="
        +preview_length+"&last_message_id="+last_message_id+"&access_token="+token.getAccess_token());
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        JAXBParser parser = new JAXBParser();
        XMLResponse message;
        message = (XMLResponse)parser.getObject(XMLResponse.class, connection.getInputStream());
        if (message.getMessage() == null){
            throw new BadParamsException();
        }
        return message.getMessage();
    }
    public static void send(int id, String message_body,AccessToken token)throws UnsupportedEncodingException,
    MalformedURLException,IOException{
        try{
        URL url = new URL(MESSAGE_SEND+"user_id="+id+"&message="+URLEncoder.encode(message_body,"UTF-8")+
        "&access_token="+token.getAccess_token());
        BufferedReader reader = connect(url);
        }catch(UnsupportedEncodingException e){
            System.out.println("message not sent");}
    }
    private static BufferedReader connect(URL url)throws IOException{
        HttpURLConnection connection =(HttpURLConnection)url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return reader;
    }
    private static List<Message> getHistory(){
        return null;
    }
    public static int getCount(){
        return response.getCount();
    }
    @XmlElement
    public void setMid(int mid) {
        this.mid = mid;
    }
    @XmlElement
    public void setUid(int uid) {
        this.uid = uid;
    }
    @XmlElement
    public void setDate(String date) {
        this.date = date;
    }
    @XmlElement
    public void setRead_state(int read_state) {
        this.read_state = read_state;
    }
    @XmlElement
    public void setOut(int out) {
        this.out = out;
    }
    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }
    @XmlElement
    public void setBody(String body) {
        this.body = body;
    }

    public int getMid() {
        return mid;
    }

    public int getUid() {
        return uid;
    }

    public String getDate() {
        return date;
    }

    public int getRead_state() {
        return read_state;
    }

    public int getOut() {
        return out;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
