package hotel;
/**
 * Люкс-комната. Наследник ProRoom.
 * Цена по умолчанию — Prices.LUX.
 * Имеет защищённый конструктор для наследников (например, UltraLuxRoom).
 */
public class LuxRoom extends ProRoom {
    public LuxRoom(int roomNumber) {
        super(roomNumber, Prices.LUX.getPrice());
    }

    // Защищённый конструктор для наследников (UltraLuxRoom)
    protected LuxRoom(int roomNumber, int pricePerNight) {
        super(roomNumber, pricePerNight);
    }
}