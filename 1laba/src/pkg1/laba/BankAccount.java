package pkg1.laba;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class BankAccount {
    // Поля класса
    private String ownerName;           // имя владельца
    private int balance;                 // баланс (целочисленный)
    private LocalDateTime openingDate;   // дата открытия
    private boolean isLocked;             // заблокирован ли счёт
    private String accountNumber;         // номер счёта (доп. задание 2)

    // Конструктор, принимающий только имя владельца
    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
        this.balance = 0;
        this.openingDate = LocalDateTime.now();
        this.isLocked = false;
        this.accountNumber = generateAccountNumber();
    }

    // Генерация номера счёта в формате XXXXXXXX (доп. задание 2)
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    // Метод пополнения счёта
    public boolean deposit(int amount) {
        if (amount <= 0 || isLocked) {
            return false; // сумма должна быть положительной, счёт не должен быть заблокирован
        }
        balance += amount;
        return true;
    }

    // Метод снятия денег
    public boolean withdraw(int amount) {
        if (amount <= 0 || isLocked || amount > balance) {
            return false; // проверка на положительность, блокировку и достаточность средств
        }
        balance -= amount;
        return true;
    }

    // Метод перевода на другой счёт
    public boolean transfer(BankAccount otherAccount, int amount) {
        if (otherAccount == null || amount <= 0 || isLocked || otherAccount.isLocked) {
            return false;
        }
        if (this.withdraw(amount)) {
            if (otherAccount.deposit(amount)) {
                return true;
            } else {
                // если не удалось зачислить (например, другой счёт заблокирован),
                // нужно вернуть деньги обратно на текущий счёт
                this.balance += amount;
                return false;
            }
        }
        return false;
    }

    // Геттеры
    public String getOwnerName() {
        return ownerName;
    }

    public int getBalance() {
        return balance;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    // Сеттер для блокировки (может пригодиться)
    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    // Доп. задание 1: переопределение toString
    @Override
    public String toString() {
        return "BankAccount{" +
                "ownerName='" + ownerName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", openingDate=" + openingDate +
                ", isLocked=" + isLocked +
                '}';
    }

    // Доп. задание 2,3: переопределение equals и hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return balance == that.balance &&
                isLocked == that.isLocked &&
                Objects.equals(ownerName, that.ownerName) &&
                Objects.equals(openingDate, that.openingDate) &&
                Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerName, balance, openingDate, isLocked, accountNumber);
    }
}