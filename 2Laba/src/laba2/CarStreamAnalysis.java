package laba2;
import java.util.*;
import java.util.stream.Collectors;

public class CarStreamAnalysis {
    public static void main(String[] args) {
        // Создаём список автомобилей (используем класс Car)
        List<Car> cars = Arrays.asList(
                new Car("VIN001", "Camry", "Toyota", 2020, 30000, 25000),
                new Car("VIN002", "X5", "BMW", 2022, 10000, 55000),
                new Car("VIN003", "Model S", "Tesla", 2021, 45000, 80000),
                new Car("VIN004", "A4", "Audi", 2019, 60000, 20000),
                new Car("VIN005", "Q5", "Audi", 2023, 5000, 45000),
                new Car("VIN006", "Camry", "Toyota", 2018, 70000, 18000),
                new Car("VIN007", "Mustang", "Ford", 2022, 15000, 35000),
                new Car("VIN008", "Focus", "Ford", 2017, 80000, 12000)
        );

        // 1. Отфильтровать машины с пробегом < 50 000 км
        List<Car> lowMileageCars = cars.stream()
                .filter(car -> car.getMileage() < 50000)
                .collect(Collectors.toList());
        System.out.println("=== Машины с пробегом менее 50 000 км ===");
        lowMileageCars.forEach(System.out::println);

        // 2. Отсортировать по цене (по убыванию)
        List<Car> sortedByPriceDesc = cars.stream()
                .sorted((c1, c2) -> Double.compare(c2.getPrice(), c1.getPrice()))
                .collect(Collectors.toList());

        // 3. Вывести топ-3 самые дорогие машины
        System.out.println("\n=== Топ-3 самых дорогих машин ===");
        sortedByPriceDesc.stream()
                .limit(3)
                .forEach(System.out::println);

        // 4. Посчитать средний пробег всех машин
        double avgMileage = cars.stream()
                .mapToInt(Car::getMileage)
                .average()
                .orElse(0.0);
        System.out.printf("\n=== Средний пробег всех машин: %.2f км ===%n", avgMileage);

        // 5. Сгруппировать машины по производителю
        Map<String, List<Car>> carsByManufacturer = cars.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));
        System.out.println("\n=== Группировка по производителю ===");
        carsByManufacturer.forEach((manufacturer, carList) -> {
            System.out.println(manufacturer + ": " + carList);
        });
    }
}