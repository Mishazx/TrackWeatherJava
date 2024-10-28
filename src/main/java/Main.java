import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Console cli = new Console();
        cli.mainFunction();

        Map<String, Number> output = cli.getResult();

        double lat = output.get("lat").doubleValue();
        double lon = output.get("lon").doubleValue();
        int count = output.get("count").intValue();

        WeatherClient weather = new WeatherClient();
        weather.result(lat, lon, count);
    }
}
