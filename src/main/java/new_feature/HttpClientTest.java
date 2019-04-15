package new_feature;

import com.google.common.base.Joiner;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fengcaiwen
 * @since 4/9/2019
 */
public class HttpClientTest {
    public static void main(String[] args) throws Exception {
        String uri = "http://sindar.ixiaolu.com/user_profile/info";
        syncGet(uri);

    }

    private static void syncGet(String uri) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("gender", "1");
        map.put("login_user_id", "12861553");
        map.put("session_id", "1555069126ac3685ca0add75cefa5641");
        map.put("user_interest", "14");
        String parameters = Joiner.on("&").join(map.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.toList()));

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).POST(HttpRequest.BodyPublishers.ofString(parameters)).timeout(Duration.ofSeconds(1)).build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
