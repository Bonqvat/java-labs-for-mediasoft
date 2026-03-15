package hotel;

/**
 * Расширение интерфейса RoomService для люксовых комнат.
 * Параметр T ограничен типом LuxRoom и его наследниками.
 * Добавляет метод доставки еды.
 */
public interface LuxRoomService<T extends LuxRoom> extends RoomService<T> {
    void foodDelivery(T room); // доставка еды в номер
}