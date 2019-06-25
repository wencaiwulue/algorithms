package newfeature;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author fengcaiwen
 * @since 4/9/2019
 */
public class HttpClientTest {

    @Autowired
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        String uri = "http://sindar.ixiaolu.com/user_profile/info";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("gender", "1");
        paramMap.put("login_user_id", "12861553");
        paramMap.put("session_id", "1555069126ac3685ca0add75cefa5641");
        paramMap.put("user_interest", "14");
//        syncGet(uri, paramMap);
        asyncPost(uri, paramMap);

    }

    private static void syncGet(String uri, Map<String, ?> map) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).POST(HttpRequest.BodyPublishers.ofString(generateGetUrlParams(map))).headers("content-type", "application/json", "charset", "utf-8").timeout(Duration.ofSeconds(1)).build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    private static void syncGet(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());

    }

    private static void asyncPost(String uri, Map<String, ?> map) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(map))).build();
        CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        completableFuture.whenComplete((response, exception) -> {
            if (exception != null) {
                exception.printStackTrace();
            } else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
            }
        }).join();
    }

    private static String generateGetUrlParams(Map paramMap) throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(paramMap));
        return Joiner.on("&").appendTo(new StringBuilder("?"), paramMap.entrySet()).toString();
    }
}
