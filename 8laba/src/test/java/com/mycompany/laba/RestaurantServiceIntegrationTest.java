package com.mycompany.laba;

import com.mycompany.laba.model.Restaurant;
import com.mycompany.laba.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
class RestaurantServiceIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private RestaurantService restaurantService;

    @Test
    void createAndRetrieveRestaurant() {
        Restaurant restaurant = new Restaurant("Integration Restaurant", "123 Main St", "Italian");
        Restaurant saved = restaurantService.createRestaurant(restaurant);
        assertThat(saved.getId()).isNotNull();

        Restaurant found = restaurantService.getRestaurantById(saved.getId());
        assertThat(found.getName()).isEqualTo("Integration Restaurant");
    }
}