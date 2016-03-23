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
@XmlRootElement(name ="last_seen")
@XmlType(propOrder = {"time","platform"})
public class LastSeen {
    private int time;
    private int platform;

    public int getTime() {
        return time;
    }
    @XmlElement
    public void setTime(int time) {
        this.time = time;
    }

    public int getPlatform() {
        return platform;
    }
    @XmlElement
    public void setPlatform(int platform) {
        this.platform = platform;
    }
    
    
    
}
