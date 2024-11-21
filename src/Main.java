import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nВыберите задание:");
            System.out.println("1. Работа с числами");
            System.out.println("2. Работа с названиями продуктов");
            System.out.println("3. Работа с устройствами");
            System.out.println("4. Работа с проекторами");
            System.out.println("5. Выход");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1 -> task1();
                case 2 -> task2(scanner);
                case 3 -> task3(scanner);
                case 4 -> task4(scanner);
                case 5 -> {
                    System.out.println("Завершение программы.");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // Задание 1: Работа с числами
    private static void task1() {
        List<Integer> numbers = new Random().ints(100, -999, 1000).boxed().collect(Collectors.toList());

        long positiveCount = numbers.stream().filter(n -> n > 0).count();
        long negativeCount = numbers.stream().filter(n -> n < 0).count();
        long twoDigitCount = numbers.stream().filter(n -> Math.abs(n) >= 10 && Math.abs(n) < 100).count();
        long mirroredCount = numbers.stream().filter(Main::isMirrored).count();

        System.out.println("Положительных чисел: " + positiveCount);
        System.out.println("Отрицательных чисел: " + negativeCount);
        System.out.println("Двухзначных чисел: " + twoDigitCount);
        System.out.println("Зеркальных чисел: " + mirroredCount);
    }

    private static boolean isMirrored(int number) {
        String str = Integer.toString(Math.abs(number));
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    // Задание 2: Работа с названиями продуктов
    private static void task2(Scanner scanner) {
        List<String> products = List.of("Молоко", "Хлеб", "Сыр", "Молоко", "Яблоко", "Масло", "Молоко");

        System.out.println("Все продукты:");
        products.forEach(System.out::println);

        System.out.println("\nПродукты с названием меньше 5 символов:");
        products.stream().filter(p -> p.length() < 5).forEach(System.out::println);

        System.out.print("\nВведите название продукта для подсчета: ");
        String targetProduct = scanner.nextLine();
        long count = products.stream().filter(p -> p.equalsIgnoreCase(targetProduct)).count();
        System.out.println("Количество '" + targetProduct + "': " + count);

        System.out.print("\nВведите букву для фильтрации: ");
        String letter = scanner.nextLine();
        products.stream().filter(p -> p.startsWith(letter)).forEach(System.out::println);

        System.out.println("\nПродукты из категории 'Молоко':");
        products.stream().filter(p -> p.equalsIgnoreCase("Молоко")).forEach(System.out::println);
    }

    // Задание 3: Работа с устройствами
    private static void task3(Scanner scanner) {
        List<Device> devices = List.of(
            new Device("Телефон", 2020, 50000, "Черный", "Электроника"),
            new Device("Ноутбук", 2022, 80000, "Серый", "Компьютер"),
            new Device("Планшет", 2021, 40000, "Белый", "Электроника")
        );

        System.out.println("Все устройства:");
        devices.forEach(System.out::println);

        System.out.println("\nУстройства черного цвета:");
        devices.stream().filter(d -> d.color.equalsIgnoreCase("Черный")).forEach(System.out::println);

        System.out.println("\nУстройства 2021 года выпуска:");
        devices.stream().filter(d -> d.year == 2021).forEach(System.out::println);

        System.out.println("\nУстройства дороже 45000:");
        devices.stream().filter(d -> d.price > 45000).forEach(System.out::println);

        System.out.println("\nУстройства типа 'Электроника':");
        devices.stream().filter(d -> d.type.equalsIgnoreCase("Электроника")).forEach(System.out::println);

        System.out.println("\nУстройства из диапазона 2020–2022 годов:");
        devices.stream().filter(d -> d.year >= 2020 && d.year <= 2022).forEach(System.out::println);
    }

    // Задание 4: Работа с проекторами
    private static void task4(Scanner scanner) {
        List<Projector> projectors = List.of(
            new Projector("BenQ X1", 2023, 40000, "BenQ"),
            new Projector("Epson Y2", 2022, 50000, "Epson"),
            new Projector("Sony Z3", 2023, 60000, "Sony")
        );

        System.out.println("Все проекторы:");
        projectors.forEach(System.out::println);

        System.out.println("\nПроекторы производителя 'Sony':");
        projectors.stream().filter(p -> p.manufacturer.equalsIgnoreCase("Sony")).forEach(System.out::println);

        System.out.println("\nПроекторы текущего года:");
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        projectors.stream().filter(p -> p.year == currentYear).forEach(System.out::println);

        System.out.println("\nПроекторы дороже 45000:");
        projectors.stream().filter(p -> p.price > 45000).forEach(System.out::println);

        System.out.println("\nПроекторы, отсортированные по цене (возрастание):");
        projectors.stream().sorted(Comparator.comparingDouble(Projector::getPrice)).forEach(System.out::println);

        System.out.println("\nПроекторы, отсортированные по цене (убывание):");
        projectors.stream().sorted(Comparator.comparingDouble(Projector::getPrice).reversed()).forEach(System.out::println);

        System.out.println("\nПроекторы, отсортированные по году выпуска (возрастание):");
        projectors.stream().sorted(Comparator.comparingInt(Projector::getYear)).forEach(System.out::println);

        System.out.println("\nПроекторы, отсортированные по году выпуска (убывание):");
        projectors.stream().sorted(Comparator.comparingInt(Projector::getYear).reversed()).forEach(System.out::println);

    }
}

// Класс Device
class Device {
    String name;
    int year;
    double price;
    String color;
    String type;

    public Device(String name, int year, double price, String color, String type) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.color = color;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %d, %.2f, %s)", name, type, year, price, color);
    }
}

// Класс Projector
class Projector {
    String name;
    int year;
    double price;
    String manufacturer;

    public Projector(String name, int year, double price, String manufacturer) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %d, %.2f)", name, manufacturer, year, price);
    }
}

