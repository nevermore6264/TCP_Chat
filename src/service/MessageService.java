/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import connection.Connects;
import model.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Datnt
 */
public class MessageService {

    UserService userService = UserService.getInstance();
    private static MessageService instance;

    private MessageService() {

    }

    public static MessageService getInstance() {
        if (instance == null) {
            instance = new MessageService();
        }
        return instance;
    }

    public boolean saveMessage(Message message) {

        String sql = "INSERT INTO messages (content, create_date, sender_id, receiver_id) VALUES (?,?,?,?)";
        try {
            Connection con = Connects.getConnect();
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, message.getContent());
            ps.setString(2, message.getDate());
            ps.setInt(3, userService.findIdByName(message.getUsername()));
            ps.setInt(4, userService.findIdByName(message.getReceiver()));

            int rs = ps.executeUpdate();

            return rs > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
