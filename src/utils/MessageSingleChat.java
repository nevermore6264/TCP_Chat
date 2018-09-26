/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;

/**
 *
 * @author Khoand
 */
public class MessageSingleChat {
    
    private static MessageSingleChat instance;
    
    private MessageSingleChat(){
        
    }
    
    public static MessageSingleChat getInstance(){
        if(instance == null){
            instance = new MessageSingleChat();
        }
        return instance;
    }
    
    public boolean sendMessageSingleChat(Socket client, String content) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(content);
            bw.newLine();
            bw.flush();
            return true;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
