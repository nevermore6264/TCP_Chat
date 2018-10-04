package api;

import com.google.gson.Gson;
import model.UserView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import model.MessageView;

public class CallViewHistoryAPI {

    private static CallViewHistoryAPI instance;

    public CallViewHistoryAPI() {
    }

    public static CallViewHistoryAPI getInstance() {
        if (instance == null) {
            instance = new CallViewHistoryAPI();
        }
        return instance;
    }

    public MessageView getListHistoryApi() {
        MessageView messageView = null;
        try {
            Gson gson = new Gson();
            String json = "";
            URL url = new URL(String.valueOf("http://localhost:8080/api/messages/"));
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
            messageView = gson.fromJson(json, MessageView.class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messageView;
    }

}
