package hotel;

/**
 * Перечисление для хранения цен на разные категории комнат.
 * Позволяет централизованно задавать стоимость и избегать магических чисел.
 */
public enum Prices {
    ECONOMY(1500),
    STANDARD(3000),
    LUX(7000),
    ULTRA_LUX(15000);

    private final int price; // цена в рублях за ночь

    Prices(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}