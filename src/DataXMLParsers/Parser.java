/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataXMLParsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Nekres
 */
public interface Parser {
    
    public Object getObjectFromFile(File file, Class c) throws JAXBException;
    public void saveObjectToFile(File file, Object o) throws JAXBException;
    public Object getObject(Class c, InputStream ss) throws JAXBException;
    public void sendObject() throws JAXBException;
    
}
