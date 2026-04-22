package com.dil3kx._laba;

import com.dil3kx._laba.model.*;
import com.dil3kx._laba.service.RestaurantService;
import com.dil3kx._laba.service.RatingService;
import com.dil3kx._laba.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Класс для инициализации тестовых данных и демонстрации работы приложения.
 * Реализует интерфейс CommandLineRunner, метод run выполняется после старта Spring-контекста.
 * Это соответствует дополнительному требованию: "реализовать класс, наследующий CommandLineRunner".
 *
 * В задании также допускается использование @PostConstruct, но CommandLineRunner удобнее.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final VisitorService visitorService;
    private final RestaurantService restaurantService;
    private final RatingService ratingService;

    /**
     * Конструктор для внедрения сервисов.
     */
    public DataInitializer(VisitorService visitorService,
                           RestaurantService restaurantService,
                           RatingService ratingService) {
        this.visitorService = visitorService;
        this.restaurantService = restaurantService;
        this.ratingService = ratingService;
    }

    /**
     * Метод, выполняющийся при запуске приложения.
     * Создаёт тестовых посетителей, рестораны, добавляет оценки и демонстрирует пересчёт рейтинга.
     */
    @Override
    public void run(String... args) {
        log.info("=== Инициализация тестовых данных ===");

        // --- Создание посетителей (один анонимный) ---
        Visitor v1 = new Visitor(null, "Иван Петров", 30, Visitor.Gender.MALE);
        Visitor v2 = new Visitor(null, "Мария Смирнова", 25, Visitor.Gender.FEMALE);
        Visitor v3 = new Visitor(null, null, 22, Visitor.Gender.FEMALE); // имя = null -> аноним

        v1 = visitorService.save(v1);
        v2 = visitorService.save(v2);
        v3 = visitorService.save(v3);

        log.info("Посетители сохранены: id={} имя={}, id={} имя={}, id={} имя={}",
                v1.getId(), v1.getName(), v2.getId(), v2.getName(), v3.getId(), v3.getName());

        // --- Создание ресторанов ---
        Restaurant r1 = new Restaurant(
                null,
                "Итальяно",
                "Уютный ресторан итальянской кухни",
                CuisineType.ITALIAN,
                new BigDecimal("2500.00"),
                BigDecimal.ZERO
        );

        Restaurant r2 = new Restaurant(
                null,
                "Пекин",
                "Китайская кухня в центре города",
                CuisineType.CHINESE,
                new BigDecimal("1800.00"),
                BigDecimal.ZERO
        );

        r1 = restaurantService.save(r1);
        r2 = restaurantService.save(r2);

        log.info("Рестораны сохранены: id={} название={}, id={} название={}",
                r1.getId(), r1.getName(), r2.getId(), r2.getName());

        // --- Добавление оценок ---
        ratingService.save(new Rating(v1.getId(), r1.getId(), 5, "Отличное место! Очень вкусно."));
        ratingService.save(new Rating(v2.getId(), r1.getId(), 4, "Хорошо, но дороговато."));
        ratingService.save(new Rating(v1.getId(), r2.getId(), 3, "Средненько."));
        ratingService.save(new Rating(v3.getId(), r2.getId(), 5, null)); // анонимный отзыв без текста

        log.info("Оценки сохранены. Проверим средние оценки ресторанов:");

        // Вывод текущего состояния ресторанов с их средними оценками
        restaurantService.findAll().forEach(r ->
                log.info("Ресторан '{}' (id={}): средняя оценка = {}",
                        r.getName(), r.getId(), r.getAverageRating())
        );

        // --- Демонстрация удаления оценки и пересчёта рейтинга ---
        log.info("Удаляем оценку от посетителя id={} для ресторана id={} (Иван -> Итальяно)",
                v1.getId(), r1.getId());
        ratingService.remove(v1.getId(), r1.getId());

        log.info("После удаления оценки:");
        restaurantService.findAll().forEach(r ->
                log.info("Ресторан '{}' (id={}): средняя оценка = {}",
                        r.getName(), r.getId(), r.getAverageRating())
        );

        log.info("=== Инициализация завершена ===");
    }
}