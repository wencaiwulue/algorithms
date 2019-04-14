//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.concurrent.CompletableFuture;
//
//public class HttpClientTest {
//    public static void main(String[] args) throws Exception {
//        String uri = "http://t.weather.sojson.com/api/weather/city/101030100";
//        syncGet(uri);
//        asyncGet(uri);
//    }
//
//    private static void syncGet(String uri) throws Exception {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
//        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.statusCode());
//        System.out.println(response.body());
//
//    }
//
//    private static void asyncGet(String uri) throws Exception {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
//        CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
////        completableFuture.whenComplete((response, exception) -> {
////            if (exception != null) {
////                exception.printStackTrace();
////            } else {
////                System.out.println(response.statusCode());
////                System.out.println(response.body());
////            }
////        }).join();
//        HttpResponse<String> httpResponse = completableFuture.join();
//        System.out.println(httpResponse.statusCode());
//        System.out.println(httpResponse.body());
//
//
//    }
//}
