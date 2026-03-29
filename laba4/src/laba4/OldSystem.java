package laba4;

// --- ТЕСТОВЫЙ КЛАСС ДЛЯ ПРОВЕРКИ АННОТАЦИЙ ---
@DeprecatedEx(message = "Используйте NewEngine")
public class OldSystem {
    
    @DeprecatedEx(message = "Используйте метод start()")
    public void oldStart() {
    }
}