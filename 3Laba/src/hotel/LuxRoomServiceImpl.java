package hotel;

/**
 * Реализация LuxRoomService для люксовых комнат.
 * Наследует базовую функциональность от StandardRoomService (clean, reserve, free)
 * и добавляет метод foodDelivery.
 */
public class LuxRoomServiceImpl<T extends LuxRoom> extends StandardRoomService<T> implements LuxRoomService<T> {
    @Override
    public void foodDelivery(T room) {
        System.out.println(" Еда доставлена в VIP-комнату №" + room.getRoomNumber());
    }
}