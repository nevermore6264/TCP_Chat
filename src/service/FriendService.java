package service;

import connection.Connects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendService {
    private static FriendService instance;
    private static UserService userService = UserService.getInstance();

    private FriendService() {
    }

    public static FriendService getInstance() {
        if (instance == null) {
            instance = new FriendService();
        }
        return instance;
    }

    public boolean isExistInvitation(Integer id, String name) {
        try {
            Connection con = Connects.getConnect();
            String sql = "SELECT friends.id,friends.user_friend_id FROM friends WHERE user_id = ? AND user_friend_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int userFriendID = userService.findIdByName(name);
            ps.setInt(2, userFriendID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
