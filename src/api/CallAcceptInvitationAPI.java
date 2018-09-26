package api;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class CallAcceptInvitationAPI {
    private static CallAcceptInvitationAPI instance;

    public CallAcceptInvitationAPI() {
    }

    public static CallAcceptInvitationAPI getInstance() {
        if (instance == null) {
            instance = new CallAcceptInvitationAPI();
        }
        return instance;
    }

    // HTTP POST request
    public void sendPut(Integer userFriendID, String token, String userID) throws Exception {

        String url = "http://localhost:8080/api/users/" + userFriendID + "/friends";

        String payload = "{" +
                "\"userFriend\" : \"" + userID + "\"" +
                "}";
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut request = new HttpPut(url);
        request.setEntity(entity);
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        request.setHeader("token", token);

        HttpResponse response = httpClient.execute(request);
        //System.out.println(response.getStatusLine().getStatusCode());
    }

}
