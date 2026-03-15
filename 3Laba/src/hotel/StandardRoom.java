package hotel;

/**
 * Стандартная комната. Наследник ProRoom.
 * Цена берётся из Prices.STANDARD.
 */
public class StandardRoom extends ProRoom {
    public StandardRoom(int roomNumber) {
        super(roomNumber, Prices.STANDARD.getPrice());
    }
}