package hotel;

public class Main {
    public static void main(String[] args) {
        // Создание комнат
        EconomyRoom ecoRoom = new EconomyRoom(101);
        StandardRoom stdRoom = new StandardRoom(201);
        LuxRoom luxRoom = new LuxRoom(301);
        UltraLuxRoom ultraLuxRoom = new UltraLuxRoom(401);

        // Сервисы
        RoomService<Room> generalService = new StandardRoomService<>();
        LuxRoomService<LuxRoom> luxService = new LuxRoomServiceImpl<>();

        System.out.println("--- ТЕСТ: Базовые операции ---");
        System.out.println(ecoRoom);
        generalService.clean(ecoRoom);
        generalService.reserve(ecoRoom);

        System.out.println("\n--- ТЕСТ: Выброс кастомного исключения ---");
        try {
            generalService.reserve(ecoRoom); // повторное бронирование
        } catch (RoomAlreadyBookedException e) {
            System.out.println(e.getMessage());
        }

        generalService.free(ecoRoom);

        System.out.println("\n--- ТЕСТ: LuxRoomService ---");
        System.out.println(ultraLuxRoom);
        luxService.reserve(ultraLuxRoom);
        luxService.foodDelivery(ultraLuxRoom);
        luxService.free(ultraLuxRoom);

        // Проверка, что нельзя вызвать foodDelivery для не-люксовой комнаты
        // luxService.foodDelivery(ecoRoom); // Ошибка компиляции!
    }
}