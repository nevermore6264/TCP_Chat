package api;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class CallRegisterAPI {

    private static CallRegisterAPI instance;

    public CallRegisterAPI() {
    }

    public static CallRegisterAPI getInstance() {
        if (instance == null) {
            instance = new CallRegisterAPI();
        }
        return instance;
    }

    // HTTP POST request
    public void sendPost(String userName) throws Exception {

        String url = "http://localhost:8080/api/users/";

        String payload = "{" +
                "\"userName\" : \"" + userName + "\"" +
                "}";
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.setEntity(entity);
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpResponse response = httpClient.execute(request);

        //System.out.println(response.getStatusLine().getStatusCode());
    }
}
