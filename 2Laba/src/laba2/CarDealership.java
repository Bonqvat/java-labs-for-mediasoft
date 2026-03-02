package laba2;

import java.util.*;
import java.util.stream.Collectors;

public class CarDealership {
    private List<Car> cars = new ArrayList<>();

    // Добавить машину с проверкой дубликата по VIN
    public boolean addCar(Car car) {
        if (cars.stream().anyMatch(c -> c.getVin().equals(car.getVin()))) {
            return false; // дубликат не добавляется
        }
        cars.add(car);
        return true;
    }

    // Найти все машины указанного производителя
    public List<Car> findByManufacturer(String manufacturer) {
        return cars.stream()
                .filter(c -> c.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    // Средняя цена машин определённого типа
    public double averagePriceByType(CarType type) {
        return cars.stream()
                .filter(c -> c.getType() == type)
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0.0);
    }

    // Список машин, отсортированных по году (от новых к старым)
    public List<Car> sortedByYearDesc() {
        return cars.stream()
                .sorted((c1, c2) -> Integer.compare(c2.getYear(), c1.getYear()))
                .collect(Collectors.toList());
    }

    // Количество машин каждого типа
    public Map<CarType, Long> countByType() {
        return cars.stream()
                .collect(Collectors.groupingBy(Car::getType, Collectors.counting()));
    }

    // Самая старая машина
    public Optional<Car> oldestCar() {
        return cars.stream().min(Comparator.comparingInt(Car::getYear));
    }

    // Самая новая машина
    public Optional<Car> newestCar() {
        return cars.stream().max(Comparator.comparingInt(Car::getYear));
    }

    // Меню для взаимодействия с пользователем
    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Меню автоцентра ---");
            System.out.println("1. Добавить машину");
            System.out.println("2. Найти по производителю");
            System.out.println("3. Средняя цена по типу");
            System.out.println("4. Сортировка по году (новые ? старые)");
            System.out.println("5. Статистика по типам");
            System.out.println("6. Самая старая и новая машина");
            System.out.println("0. Выход");
            System.out.print("Выберите опцию: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введите число!");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Введите VIN: ");
                    String vin = scanner.nextLine();
                    System.out.print("Модель: ");
                    String model = scanner.nextLine();
                    System.out.print("Производитель: ");
                    String man = scanner.nextLine();
                    System.out.print("Год: ");
                    int year;
                    try {
                        year = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат года");
                        break;
                    }
                    System.out.print("Пробег: ");
                    int mileage;
                    try {
                        mileage = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат пробега");
                        break;
                    }
                    System.out.print("Цена: ");
                    double price;
                    try {
                        price = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат цены");
                        break;
                    }
                    System.out.print("Тип (SEDAN, SUV, ELECTRIC, HATCHBACK, COUPE): ");
                    String typeStr = scanner.nextLine().toUpperCase();
                    CarType type;
                    try {
                        type = CarType.valueOf(typeStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Неверный тип");
                        break;
                    }

                    Car car = new Car(vin, model, man, year, mileage, price, type);
                    if (addCar(car)) {
                        System.out.println("Машина добавлена.");
                    } else {
                        System.out.println("Ошибка: VIN уже существует!");
                    }
                    break;

                case 2:
                    System.out.print("Введите производителя: ");
                    String m = scanner.nextLine();
                    List<Car> found = findByManufacturer(m);
                    if (found.isEmpty()) {
                        System.out.println("Машины не найдены.");
                    } else {
                        System.out.println("Найденные машины:");
                        found.forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.print("Введите тип (SEDAN, SUV, ELECTRIC, HATCHBACK, COUPE): ");
                    String tStr = scanner.nextLine().toUpperCase();
                    CarType t;
                    try {
                        t = CarType.valueOf(tStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Неверный тип");
                        break;
                    }
                    double avg = averagePriceByType(t);
                    System.out.printf("Средняя цена для типа %s: %.2f%n", t, avg);
                    break;

                case 4:
                    List<Car> sorted = sortedByYearDesc();
                    if (sorted.isEmpty()) {
                        System.out.println("Список пуст.");
                    } else {
                        System.out.println("Машины по году (новые ? старые):");
                        sorted.forEach(System.out::println);
                    }
                    break;

                case 5:
                    Map<CarType, Long> stats = countByType();
                    if (stats.isEmpty()) {
                        System.out.println("Нет машин.");
                    } else {
                        System.out.println("Количество машин каждого типа:");
                        stats.forEach((typeKey, count) -> System.out.println(typeKey + ": " + count));
                    }
                    break;

                case 6:
                    oldestCar().ifPresentOrElse(
                            c -> System.out.println("Самая старая: " + c),
                            () -> System.out.println("Нет машин")
                    );
                    newestCar().ifPresentOrElse(
                            c -> System.out.println("Самая новая: " + c),
                            () -> System.out.println("Нет машин")
                    );
                    break;

                case 0:
                    System.out.println("Выход.");
                    return;

                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    public static void main(String[] args) {
        CarDealership dealership = new CarDealership();

        // Добавим несколько тестовых машин для демонстрации
        dealership.addCar(new Car("VIN111", "Camry", "Toyota", 2020, 30000, 25000, CarType.SEDAN));
        dealership.addCar(new Car("VIN222", "X5", "BMW", 2022, 10000, 55000, CarType.SUV));
        dealership.addCar(new Car("VIN333", "Model S", "Tesla", 2021, 45000, 80000, CarType.ELECTRIC));
        dealership.addCar(new Car("VIN444", "Q5", "Audi", 2023, 5000, 45000, CarType.SUV));
        dealership.addCar(new Car("VIN555", "Mustang", "Ford", 2022, 15000, 35000, CarType.COUPE));

        dealership.runMenu();
    }
}