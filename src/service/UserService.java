package service;

import connection.Connects;
import utils.DateFormat;
import utils.DateTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class UserService {

    private static UserService instance;

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private static final int TIME_OUT = 2;

    public boolean isExistsUser(String username) {
        try {
//            BasicDataSource basicDataSource = DataSource.getInstance().getBds();
//            Connection con = basicDataSource.getConnection();
            Connection con = Connects.getConnect();
            String sql = "SELECT users.id, user_name FROM users WHERE user_name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int findIdByName(String username) {
        int id = 0;

        try {
            //BasicDataSource basicDataSource = DataSource.getInstance().getBds();
            //Connection con = basicDataSource.getConnection();
            Connection con = Connects.getConnect();
            String sql = "SELECT users.id FROM users WHERE user_name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public String genToken(int id, String userName) {

        try {
            //BasicDataSource basicDataSource = DataSource.getInstance().getBds();
            //Connection con = basicDataSource.getConnection();
            Connection con = Connects.getConnect();
            Date date = DateTime.getCurrentDate();
            String createDate = DateFormat.formatDate(date);
            date.setHours(date.getHours() + TIME_OUT);
            String expectDate = DateFormat.formatDate(date);
            String sql = "INSERT INTO tokens(`key`, expire_date,user_id,create_date) VALUES (MD5(?), ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userName + date);
            ps.setString(2, expectDate);
            ps.setInt(3, id);
            ps.setString(4, createDate);
            ps.executeUpdate();
            return getToken(id, createDate, expectDate);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getToken(int id, String createDate, String expectDate) {

        try {
            //BasicDataSource basicDataSource = DataSource.getInstance().getBds();
            //Connection con = basicDataSource.getConnection();
            Connection con = Connects.getConnect();
            String sql = "SELECT tokens.key FROM tokens WHERE expire_date = ? && user_id = ? && create_date = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, expectDate);
            ps.setInt(2, id);
            ps.setString(3, createDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("key");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String getTokenExpires(int id) {
        try {
            //BasicDataSource basicDataSource = DataSource.getInstance().getBds();
            //Connection con = basicDataSource.getConnection();
            Connection con = Connects.getConnect();
            String sql = "SELECT tokens.key FROM tokens WHERE user_id = ? and expire_date >  now()";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("key");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
