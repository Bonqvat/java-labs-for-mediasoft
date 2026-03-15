package hotel;

import java.util.Random;

/**
 * Абстрактный базовый класс для всех типов комнат.
 * Содержит общие поля: номер, макс. количество человек, цена за ночь, статус брони.
 * Запрещает прямое создание объектов (abstract).
 */
public abstract class Room {
    private int roomNumber;       // номер комнаты
    private int maxPeople;        // максимальное количество человек (генерируется случайно)
    private int pricePerNight;    // цена за ночь (берётся из перечисления Prices)
    private boolean isBooked;     // забронирована ли комната (true - да)

    /**
     * Конструктор с параметрами. Количество человек генерируется случайно от 1 до 4.
     * @param roomNumber номер комнаты
     * @param pricePerNight цена за ночь
     */
    public Room(int roomNumber, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.maxPeople = new Random().nextInt(4) + 1; // случайное число 1..4
        this.isBooked = false; // изначально свободна
    }

    // Геттеры и сеттеры
    public int getRoomNumber() { return roomNumber; }
    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { isBooked = booked; }

    /**
     * Возвращает строковое представление комнаты (например, "LuxRoom №301 (Мест: 2, Цена: 7000 руб/ночь)")
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " №" + roomNumber +
               " (Мест: " + maxPeople + ", Цена: " + pricePerNight + " руб/ночь)";
    }
}