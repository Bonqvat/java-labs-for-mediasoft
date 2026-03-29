package laba4;

import java.lang.reflect.*;
import java.util.function.*;
import java.util.StringJoiner;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        
        System.out.println("=== ЧАСТЬ 1: ЛЯМБДА-ВЫРАЖЕНИЯ ===");

        // 1.1 Printable
        Printable p = () -> System.out.println("Печать статуса...");
        p.print();

        // 1.2 Predicate (null и пустота)
        Predicate<String> notNull = s -> s != null;
        Predicate<String> notEmpty = s -> !s.isEmpty();
        System.out.println("Проверка строки: " + notNull.and(notEmpty).test("Java"));

        // 1.3 Predicate (Буквы)
        Predicate<String> checkLetters = s -> (s.startsWith("J") || s.startsWith("N")) && s.endsWith("A");
        System.out.println("Проверка J/N...A: " + checkLetters.test("JAVA"));

        // 1.4* Consumer (HeavyBox)
        Consumer<HeavyBox> ship = b -> System.out.print("Отгрузили " + b.content + " (" + b.weight + "кг). ");
        Consumer<HeavyBox> send = b -> System.out.println("Отправляем!");
        ship.andThen(send).accept(new HeavyBox(100, "Руль PXN"));

        // 1.5* Function (Числа)
        Function<Integer, String> numTest = n -> (n > 0) ? "Положительное" : (n < 0) ? "Отрицательное" : "Ноль";
        System.out.println("Число 10: " + numTest.apply(10));

        // 1.6* Supplier (Random)
        Supplier<Integer> rnd = () -> new Random().nextInt(11);
        System.out.println("Случайное число: " + rnd.get());


        System.out.println("\n=== ЧАСТЬ 2: РЕФЛЕКСИЯ И АННОТАЦИИ ===");

        // 2.1 Обработка @DeprecatedEx
        Class<?> clazz = OldSystem.class;
        if (clazz.isAnnotationPresent(DeprecatedEx.class)) {
            System.out.println("! класс '" + clazz.getSimpleName() + "' устарел – альтернатива: '" + clazz.getAnnotation(DeprecatedEx.class).message() + "'");
        }
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(DeprecatedEx.class)) {
                System.out.println("! метод '" + m.getName() + "' устарел – альтернатива: '" + m.getAnnotation(DeprecatedEx.class).message() + "'");
            }
        }

        // 2.2* Сериализация в JSON
        PlayerProfile profile = new PlayerProfile();
        StringJoiner sj = new StringJoiner(", ", "{", "}");
        for (Field f : profile.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(JsonField.class)) {
                f.setAccessible(true);
                String key = f.getAnnotation(JsonField.class).name();
                Object val = f.get(profile);
                String valStr = (val instanceof String) ? "\"" + val + "\"" : String.valueOf(val);
                sj.add("\"" + key + "\": " + valStr);
            }
        }
        System.out.println("JSON: " + sj.toString());
    }
}