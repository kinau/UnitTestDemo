
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public final class App {

    public static void main(String[] args) throws IOException, ParseException {
        String s = "https://samples.openweathermap.org/data/2.5/group?id=524901,703448,2643743&units=metric&appid=439d4b804bc8187953eb36d2a8c26a02";
        URL url = new URL(s);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();

        StringBuilder sb = new StringBuilder();
        if (responseCode == 200) {
            Scanner scanner = new Scanner(conn.getInputStream());
            while(scanner.hasNext()) {
                sb.append(scanner.nextLine());
            }
        }

        String responseContent = sb.toString();
        System.out.println(String.format("ResponseCode: %s", responseCode));
        System.out.println(String.format("ResponseContent: %s", responseContent));


        JSONParser parser = new JSONParser();
       JSONObject jsonObject = (JSONObject) parser.parse(responseContent);
       String name = jsonObject.get("cnt").toString();

       System.out.println(String.format("cnt: %s", name));

    }
}
