package hotel;

/**
 * Эконом-комната. Прямой наследник Room.
 * Использует цену из перечисления Prices.ECONOMY.
 */
public class EconomyRoom extends Room {
    public EconomyRoom(int roomNumber) {
        super(roomNumber, Prices.ECONOMY.getPrice());
    }
}