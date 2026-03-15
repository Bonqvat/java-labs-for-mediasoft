package hotel;

/**
 * Ультра-люкс комната. Наследник LuxRoom.
 * Использует защищённый конструктор LuxRoom для передачи своей цены ULTRA_LUX.
 */
public class UltraLuxRoom extends LuxRoom {
    public UltraLuxRoom(int roomNumber) {
        super(roomNumber, Prices.ULTRA_LUX.getPrice());
    }
}