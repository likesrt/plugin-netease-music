package la.moony.music.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class NeteaseHttpClient {
    public JsonNode neteaseHttp(String url) {
        String referer = "http://music.163.com/";
        String cookie = "appver=1.5.0.75771;";
        String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 7_1_2 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Version/7.0 Mobile/11D257 Safari/9537.53";
        JsonNode jsonNode =null;
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", cookie);
            connection.setRequestProperty("User-Agent", userAgent);
            connection.setRequestProperty("Referer", referer);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                ObjectMapper objectMapper = new ObjectMapper();
                jsonNode = objectMapper.readTree(response.toString());
                return jsonNode;
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
                return jsonNode;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return jsonNode;
        }
    }

    public JsonNode comments(String id) {
        String key = "R_SO_4_" + id;

        String url = "http://music.163.com/api/v1/resource/comments/" + key + "/?rid=" + key + "&offset=0&total=false&limit=0";
        JsonNode jsonNode =null;
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(30000); // 30 seconds timeout
            connection.setReadTimeout(30000);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Referer", requestUrl.getProtocol() + "://" + requestUrl.getHost());
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseNode = objectMapper.readTree(connection.getInputStream());

            int code = responseNode.get("code").asInt();
            if (code == 200 && responseNode.has("hotComments")) {
                String content = responseNode.get("hotComments").toString();
                jsonNode = objectMapper.readTree(content);
                return jsonNode;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

}
