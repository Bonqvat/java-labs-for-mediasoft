package laba2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Car implements Comparable<Car> {
    private String vin;
    private String model;
    private String manufacturer;
    private int year;
    private int mileage;
    private double price;
    private CarType type; // новое поле для доп. задания

    // Конструктор для заданий 3 и 4 (без type, по умолчанию SEDAN)
    public Car(String vin, String model, String manufacturer, int year, int mileage, double price) {
        this(vin, model, manufacturer, year, mileage, price, CarType.SEDAN);
    }

    // Конструктор для дополнительного задания
    public Car(String vin, String model, String manufacturer, int year, int mileage, double price, CarType type) {
        this.vin = vin;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
        this.type = type;
    }

    // Геттеры
    public String getVin() { return vin; }
    public String getModel() { return model; }
    public String getManufacturer() { return manufacturer; }
    public int getYear() { return year; }
    public int getMileage() { return mileage; }
    public double getPrice() { return price; }
    public CarType getType() { return type; }

    // equals и hashCode только по VIN
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(vin, car.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }

    // Сортировка по году (от новых к старым)
    @Override
    public int compareTo(Car other) {
        return Integer.compare(other.year, this.year);
    }

    @Override
    public String toString() {
        return String.format("Car{vin='%s', model='%s', year=%d, mileage=%d, price=%.2f, type=%s}",
                vin, model, year, mileage, price, type);
    }

    // Демонстрация
    public static void main(String[] args) {
        Set<Car> carSet = new HashSet<>();
        carSet.add(new Car("VIN123", "Camry", "Toyota", 2020, 15000, 25000, CarType.SEDAN));
        carSet.add(new Car("VIN456", "X5", "BMW", 2022, 5000, 45000, CarType.SUV));
        carSet.add(new Car("VIN123", "Camry", "Toyota", 2020, 15000, 25000, CarType.SEDAN));

        System.out.println("Размер HashSet: " + carSet.size());
        for (Car car : carSet) {
            System.out.println(car);
        }
    }
}