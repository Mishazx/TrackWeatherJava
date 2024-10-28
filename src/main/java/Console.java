import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Console {
    double lat = 55.72;
    double lon = 37.62;
    int count = 7;

    Scanner sc = new Scanner(System.in);

    Map<String, Number> result = new HashMap<>();

    public void mainFunction() {

        System.out.println("Введите два числа (широта и долгота) через пробел: ");

        String input = sc.nextLine();
        if (!input.trim().isEmpty()) {
            String[] numbers = input.split(" ");
            try {
                this.lat = Double.parseDouble(numbers[0]);
                this.lon = Double.parseDouble(numbers[1]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Неверный ввод координат, используются значения по умолчанию.");
                System.out.println("По умолчанию г. Москва, координаты: " + lat + " " + lon);
            }
        }

        System.out.print("Введите количество дней: ");
        handleCount();

        sc.close();

        result.put("lat", this.lat);
        result.put("lon", this.lon);
        result.put("count", this.count);
    }

    public void handleCount() {
        while (true) {
            System.out.print("Введите количество дней (больше 1): ");

            if (sc.hasNextLine()) {
                String input = sc.nextLine();
                if (input.trim().isEmpty()) {
                    System.out.println("Неверный ввод, введите число больше единицы");
                    continue;
                }

                try {
                    this.count = Integer.parseInt(input);
                    if (this.count > 1) {
                        break;
                    } else {
                        System.out.println("Неверный ввод, введите число больше единицы");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Неверный ввод, введите число");
                }
            }
        }
    }

    public Map<String, Number> getResult () {
        return this.result;
    }
}
