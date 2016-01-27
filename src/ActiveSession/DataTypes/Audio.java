/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActiveSession.DataTypes;

import Account.AccessToken;
import DataXMLParsers.JAXBParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Nekres
 */
@XmlRootElement(name = "audio")
@XmlType(propOrder = {"id","owner_id","artist","title","duration","url"})
public class Audio {
    private static final String AUDIO_GET = "https://api.vk.com/method/audio.get.xml?";
    private int id;
    private int owner_id;
    private String artist;
    private String title;
    private int duration;
    private String url;
    
    public static List<Audio> get(int owner_id,AccessToken token) throws MalformedURLException, IOException, JAXBException{
        URL url = new URL(AUDIO_GET+"owner_id="+owner_id+"&access_token="+token.getAccess_token());
        List<Audio> audios;
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        XMLResponse response;
        JAXBParser parser = new JAXBParser();
        response = (XMLResponse)parser.getObject(XMLResponse.class, connection.getInputStream());
        return response.getAudio();
    }

    public int getId() {
        return id;
    }
    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public int getOwner_id() {
        return owner_id;
    }
    @XmlElement
    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getArtist() {
        return artist;
    }
    @XmlElement
    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }
    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }
    @XmlElement
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }
    @XmlElement
    public void setUrl(String url) {
        this.url = url;
    }
    
}
