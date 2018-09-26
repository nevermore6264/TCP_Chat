package utils;

import service.UserService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class Login {

    private static Login instance;
    private UserService userService = UserService.getInstance();

    private Login() {

    }

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }

    public boolean checkLogin(String name, String message, Socket client, BufferedWriter bufferedWriter, Map<String, Socket> clients) throws IOException {

        if (!userService.isExistsUser(name) || clients.containsKey(name)) {
            bufferedWriter.write("Login failed");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            return false;
        } else {
            clients.put(name, client);
            int userId = userService.findIdByName(name);
            String tokenLogin = "";
            if (userService.getTokenExpires(userId) != null) {
                System.out.println("Get last token");
                tokenLogin = userService.getTokenExpires(userId);
            } else {
                tokenLogin = userService.genToken(userId, name);
                System.out.println("Gen token");
            }
            System.out.println("Login log:" + name);
            bufferedWriter.write(tokenLogin);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            return true;
        }
    }

}
