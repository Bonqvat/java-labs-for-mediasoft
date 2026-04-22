package com.dil3kx._laba.model;

/**
 * Перечисление типов кухни ресторана.
 * Требование: "тип кухни (сделать перечисление с типами “Европейская”, “Итальянская”, “Китайская”, и т.д.)"
 * Каждый элемент имеет поле displayName для отображения на русском языке.
 */
public enum CuisineType {

    EUROPEAN("Европейская"),
    ITALIAN("Итальянская"),
    CHINESE("Китайская"),
    JAPANESE("Японская"),
    RUSSIAN("Русская"),
    MEXICAN("Мексиканская");

    // Внутреннее поле для хранения русского названия
    private final String displayName;

    /**
     * Конструктор перечисления.
     * @param displayName русское название кухни
     */
    CuisineType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Возвращает название кухни на русском языке.
     * @return строковое представление для вывода
     */
    public String getDisplayName() {
        return displayName;
    }
}