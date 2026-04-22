package com.dil3kx._laba.model;

/**
 * Класс, представляющий посетителя ресторана.
 * Требования:
 * - id (типа Long),
 * - имя (не обязательное, может быть null для анонимного отзыва),
 * - возраст (обязательное),
 * - пол (обязательное, представлен внутренним перечислением Gender).
 * 
 * Вместо Lombok все геттеры, сеттеры и конструкторы написаны явно.
 */
public class Visitor {

    private Long id;           // Уникальный идентификатор посетителя
    private String name;       // Имя посетителя (может быть null)
    private Integer age;       // Возраст посетителя
    private Gender gender;     // Пол посетителя

    /**
     * Перечисление для пола посетителя.
     */
    public enum Gender {
        MALE,   // Мужской
        FEMALE  // Женский
    }

    // Пустой конструктор (необходим для работы фреймворков)
    public Visitor() {
    }

    /**
     * Конструктор со всеми полями.
     * @param id идентификатор
     * @param name имя (может быть null)
     * @param age возраст
     * @param gender пол
     */
    public Visitor(Long id, String name, Integer age, Gender gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}