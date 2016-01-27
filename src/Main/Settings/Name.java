/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Settings;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Nekres
 */
@XmlRootElement
@XmlType(propOrder = {"real_name","short_name"})
public class Name {
    private String real_name;
    private String short_name;
    
    public Name(String real_name, String short_name) {
        this.real_name = real_name;
        this.short_name = short_name;
    }
    public Name(){}
    @XmlElement
    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }
    @XmlElement
    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getReal_name() {
        return real_name;
    }

    public String getShort_name() {
        return short_name;
    }
    
    
    
    
    
    
}
