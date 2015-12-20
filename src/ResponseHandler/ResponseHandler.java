/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResponseHandler;

/**
 *
 * @author Nekres
 */
public  class ResponseHandler {
    
    
    public static String getValue(String value1, String value2) {
        String text;
        text = value1.substring(value1.indexOf(value2)+2, value1.indexOf("\",\""));
        return text;
    }
    
}
