package laba2;
import java.util.Random;

public class CarFleet {
    public static void main(String[] args) {
        // 1. Создаём массив годов выпуска 50 случайных машин (от 2000 до 2025)
        int[] years = new int[50];
        Random random = new Random();
        for (int i = 0; i < years.length; i++) {
            years[i] = 2000 + random.nextInt(26);
        }

        // 2. Выводим только машины, выпущенные после 2015 года
        System.out.println("Машины выпущенные после 2015 года:");
        for (int year : years) {
            if (year > 2015) {
                System.out.println(year);
            }
        }

        // 3. Считаем средний возраст авто
        int currentYear = 2026;
        int sumAge = 0;
        for (int year : years) {
            sumAge += (currentYear - year);
        }
        double averageAge = (double) sumAge / years.length;
        System.out.printf("Средний возраст автомобилей: %.2f лет%n", averageAge);
    }
}