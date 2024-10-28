import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherClient {

    private static final String API_KEY = "f7360747-829b-4a66-8cad-ab8c74983a54";
    private static final String BASE_URL = "https://api.weather.yandex.ru/v2/forecast";

    public void result(double lat, double lon, int limit) {
        try {
            String response = getWeatherData(lat, lon, limit);

            if (response != null) {
                JSONObject jsonResponse = new JSONObject(response);
                System.out.println("Полный JSON ответ:");
                System.out.println(jsonResponse.toString(2));

                JSONObject fact = jsonResponse.getJSONObject("fact");
                int currentTemp = fact.getInt("temp");
                System.out.println("\nТекущая температура (факт): " + currentTemp);

                double avgTemp = calculateAverageTemp(jsonResponse, limit);
                System.out.println("\nСредняя температура за " + limit + " дней: " + avgTemp);

            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static String getWeatherData(double lat, double lon, int limit) throws Exception {
        String url = BASE_URL + "?lat=" + lat + "&lon=" + lon + "&limit=" + limit;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-Yandex-API-Key", API_KEY)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return response.body();
        } else {
            System.out.println("Error: " + response.statusCode());
            return null;
        }
    }

    public static double calculateAverageTemp(JSONObject jsonResponse, int limit) {
        JSONArray forecasts = jsonResponse.getJSONArray("forecasts");
        double totalTemp = 0;
        for (int i = 0; i < limit; i++) {
            JSONObject day = forecasts.getJSONObject(i).getJSONObject("parts").getJSONObject("day");
            totalTemp += day.getDouble("temp_avg");
        }
        return totalTemp / limit;
    }
}
