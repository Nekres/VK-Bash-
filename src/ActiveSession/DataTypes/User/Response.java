/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActiveSession.DataTypes.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Nekres
 */
@XmlRootElement(name = "response")
@XmlType(propOrder = {"user"})
public class Response {
    private User user;

    public User getUser() {
        return user;
    }
    @XmlElement
    public void setUser(User user) {
        this.user = user;
    }

    public Response() {
    }
    
}
