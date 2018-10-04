/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.net.Socket;
import java.text.ParseException;
import java.util.Map;

import model.Message;
import service.MessageService;
import service.UserService;

/**
 *
 * @author Khoand
 */
public class ReadMessage {
    
    private static ReadMessage instance;
    private MessageSingleChat messageSingleChat = MessageSingleChat.getInstance(); 
    private UserService userService = UserService.getInstance();
    private MessageService messageService = MessageService.getInstance();
    
    private ReadMessage(){
        
    }
    
    public static ReadMessage getInstance(){
        if(instance == null){
            instance = new ReadMessage();
        }
        return instance;
    }
    
    public String readMessageSingleChat(String message, String name, Socket client, Map<String, Socket> clients) throws ParseException{
        if (message.startsWith("@")) {
            String receiver = message.substring(1).split(" ")[0];
            String content = message.substring(receiver.length() + 1);
            String date = DateFormat.formatDate(DateTime.getCurrentDate());
            Message message1 = new Message(name, content, date, receiver);

            if (clients.containsKey(receiver)) {
                if (content.trim().length() == 0) {
                    messageSingleChat.sendMessageSingleChat(client, "content not empty");
                    return "content empty";
                } else {
                    //System.out.println("sended "+receiver+"-"+name+'-'+content);
                    messageSingleChat.sendMessageSingleChat(clients.get(receiver), name + " : " + content);
                    messageService.saveMessage(message1);
                    return "send success";
                }
            } else {
                if (userService.isExistsUser(receiver)) {
                    messageSingleChat.sendMessageSingleChat(client, "'" + receiver + "' not online");
                    return "not online";
                } else {
                    System.out.println("Don't find this username : " + receiver);
                    messageSingleChat.sendMessageSingleChat(client, "'" + receiver + "' is not correct");
                    return "incorrect";
                }
            }
        } else {
            messageSingleChat.sendMessageSingleChat(client, "This information is illegal !!!");
            return "error syntact";
        }
    }
}
