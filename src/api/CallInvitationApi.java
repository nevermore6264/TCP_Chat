package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.User;
import service.UserService;

import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author Khoand
 */
public class CallInvitationApi {

    private static CallInvitationApi instance;

    private static UserService userService = UserService.getInstance();

    public CallInvitationApi() {
    }

    public static CallInvitationApi getInstance() {
        if (instance == null) {
            instance = new CallInvitationApi();
        }
        return instance;
    }

    public List<User> getListFriendApi(String userName) {
        int id = userService.findIdByName(userName);
        List<User> list = new ArrayList<>();
        try {
            Gson gson = new Gson();
            String json = "";
            URL url = new URL(String.valueOf("http://localhost:8080//api/users/" + id + "/friends"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                json += output;
            }

            list = gson.fromJson(json,
                    new TypeToken<ArrayList<User>>() {
                    }.getType());
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
