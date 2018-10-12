/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.util.Map;

/**
 * @author Khoand
 */
public class ClientHandle {

    private static ClientHandle instance;
    private Login checkLogin = Login.getInstance();
    private ReadMessage readMessage = ReadMessage.getInstance();

    private ClientHandle() {
    }

    public static ClientHandle getInstance() {
        return new ClientHandle();
    }
    //start working with client

    public void clientHandle(Socket client, Map<String, Socket> clients) {
        String clientName = "";
        String chatWith = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            boolean isLogin = false;
            while (true) {
                String message = bufferedReader.readLine();
                if (message == null) {
                    if (isLogin) {
                        clients.remove(clientName);
                    }
                    break;
                }
                if (!isLogin) {
                    clientName = message;
                    isLogin = checkLogin.checkLogin(clientName, message, client, bufferedWriter, clients);
                    System.out.println("User Login: " + clientName + "-" + isLogin);
                } else {
                       if(message.startsWith("@logout")){
                            clients.remove(message.split(" ")[1]);
                       } else{ 
                        readMessage.readMessageSingleChat(message, clientName, client, clients);
                       }
                }
            }
        } catch (IOException e) {
            clients.remove(clientName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
