package hotel;

/**
 * Абстрактный класс для "профессиональных" комнат (более высокого уровня).
 * Наследуется от Room. Сам по себе не может быть создан (abstract).
 * Служит промежуточным звеном для StandardRoom и LuxRoom.
 */
public abstract class ProRoom extends Room {
    public ProRoom(int roomNumber, int pricePerNight) {
        super(roomNumber, pricePerNight);
    }
}