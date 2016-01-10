/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActiveSession.DataTypes;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Nekres
 */
@XmlRootElement(name = "response")
@XmlType(propOrder = {"user","count","message"})
public class XMLResponse {
    private List<Friend> user;
    private List<Message> message;
    private int message_count;
    @XmlElement
    public void setUser(List<Friend> user) {
        this.user = user;
    }
    public List<Friend> getUser() {
        return user;
    }
    @XmlElement
    public void setCount(int count) {
        this.message_count = count;
    }
    public int getCount() {
        return message_count;
    }
    @XmlElement
    public void setMessage(List<Message> message) {
        this.message = message;
    }
    public List<Message> getMessage() {
        return message;
    }
}
