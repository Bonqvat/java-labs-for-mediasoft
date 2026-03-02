package laba2;
import java.util.*;

public class CarModels {
    public static void main(String[] args) {
        // Создаём список с дубликатами
        List<String> models = new ArrayList<>(Arrays.asList(
                "Toyota Camry", "BMW X5", "Tesla Model S", "Toyota Camry",
                "Audi A4", "BMW X5", "Tesla Model 3", "Lada Vesta"
        ));

        // 1. Удаляем дубликаты через Set
        Set<String> uniqueModels = new LinkedHashSet<>(models);
        models = new ArrayList<>(uniqueModels);

        // 2. Сортируем в обратном алфавитном порядке
        models.sort(Collections.reverseOrder());
        System.out.println("Модели после сортировки (обратный порядок): " + models);

        // 3. Сохраняем в Set
        Set<String> modelSet = new TreeSet<>(Collections.reverseOrder());
        modelSet.addAll(models);

        // 4. Замена "Tesla" на "ELECTRO_CAR"
        List<String> updatedModels = new ArrayList<>();
        for (String model : models) {
            if (model.contains("Tesla")) {
                updatedModels.add("ELECTRO_CAR");
            } else {
                updatedModels.add(model);
            }
        }
        System.out.println("Модели после замены Tesla: " + updatedModels);
    }
}