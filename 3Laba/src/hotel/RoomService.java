package hotel;

/**
 * Обобщённый интерфейс для сервиса работы с комнатами.
 * Параметр T ограничен типом Room и его наследниками.
 * Содержит методы уборки, бронирования и освобождения.
 */
public interface RoomService<T extends Room> {
    void clean(T room);    // убрать комнату
    void reserve(T room);  // забронировать комнату
    void free(T room);     // освободить комнату
}