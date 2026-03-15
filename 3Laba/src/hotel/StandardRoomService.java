package hotel;

/**
 * Базовая реализация RoomService для любых типов комнат.
 * Методы выводят информацию в консоль и изменяют статус брони.
 */
public class StandardRoomService<T extends Room> implements RoomService<T> {
    @Override
    public void clean(T room) {
        System.out.println(" Комната №" + room.getRoomNumber() + " убрана.");
    }

    @Override
    public void reserve(T room) {
        // Проверка, не занята ли уже комната
        if (room.isBooked()) {
            throw new RoomAlreadyBookedException(" Ошибка: Комната №" + room.getRoomNumber() + " уже забронирована!");
        }
        room.setBooked(true); // меняем статус на "забронировано"
        System.out.println(" Комната №" + room.getRoomNumber() + " успешно забронирована.");
    }

    @Override
    public void free(T room) {
        room.setBooked(false); // меняем статус на "свободно"
        System.out.println(" Комната №" + room.getRoomNumber() + " освобождена.");
    }
}