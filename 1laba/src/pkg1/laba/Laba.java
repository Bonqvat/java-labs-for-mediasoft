package pkg1.laba;

public class Laba {
    public static void main(String[] args) {
        // Создаём два счета
        BankAccount account1 = new BankAccount("Иван Петров");
        BankAccount account2 = new BankAccount("Мария Смирнова");

        System.out.println("После создания:");
        System.out.println(account1);
        System.out.println(account2);
        System.out.println();

        // Пополнение счёта
        System.out.println("Пополнение счёта 1 на 1000: " + account1.deposit(1000));
        System.out.println("Пополнение счёта 2 на 500: " + account2.deposit(500));
        System.out.println(account1);
        System.out.println(account2);
        System.out.println();

        // Снятие денег
        System.out.println("Снятие со счёта 1 300: " + account1.withdraw(300));
        System.out.println("Снятие со счёта 2 600 (больше баланса): " + account2.withdraw(600));
        System.out.println(account1);
        System.out.println(account2);
        System.out.println();

        // Перевод
        System.out.println("Перевод со счёта 1 на счёт 2 200: " + account1.transfer(account2, 200));
        System.out.println(account1);
        System.out.println(account2);
        System.out.println();

        // Проверка на заблокированном счете
        account2.setLocked(true);
        System.out.println("Попытка перевода на заблокированный счёт: " + account1.transfer(account2, 100));
        System.out.println(account1);
        System.out.println(account2);
        System.out.println();

        // Сравнение счетов (equals)
        BankAccount account3 = new BankAccount("Иван Петров");
        System.out.println("account1 и account3 равны? " + account1.equals(account3)); // false, номера разные
        System.out.println("account1 и account1 равны? " + account1.equals(account1)); // true
    }
}