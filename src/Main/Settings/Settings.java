/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Settings;

import java.util.ArrayList;
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
    private ArrayList<Name> short_names;
    

    public ArrayList<Name> getFriend() {
        return short_names;
    }
    @XmlElement
    public void setFriend(ArrayList<Name> short_names) {
        this.short_names = short_names;
    }

    
    
    
    
}
