package hotel;

/**
 * Кастомное непроверяемое исключение (RuntimeException).
 * Выбрасывается при попытке забронировать уже занятую комнату.
 */
public class RoomAlreadyBookedException extends RuntimeException {
    public RoomAlreadyBookedException(String message) {
        super(message);
    }
}