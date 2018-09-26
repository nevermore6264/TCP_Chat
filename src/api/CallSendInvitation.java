package api;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;


public class CallSendInvitation {
    private static CallSendInvitation instance;

    public CallSendInvitation() {
    }

    public static CallSendInvitation getInstance() {
        if (instance == null) {
            instance = new CallSendInvitation();
        }
        return instance;
    }

    // HTTP POST request
    public void sendPost(Integer id, String token, String userFriend) throws Exception {

        String url = "http://localhost:8080/api/users/" + id + "/friends";

        String payload = "{" +
                "\"userFriend\" : \"" + userFriend + "\"" +
                "}";
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        org.apache.http.client.methods.HttpPost request = new HttpPost(url);
        request.setEntity(entity);
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        request.setHeader("token", token);

        HttpResponse response = httpClient.execute(request);
       // System.out.println(response.getStatusLine().getStatusCode());
    }
}