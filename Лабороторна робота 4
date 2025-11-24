import java.util.*;

// ------------------ Класс Point ------------------
class Point {
    private double x, y;
    public Point(double x, double y) { this.x = x; this.y = y; }
    public double distance(Point other) {
        return Math.sqrt(Math.pow(x - other.x,2) + Math.pow(y - other.y,2));
    }
    public String toString() { return "(" + x + ", " + y + ")"; }
}

// ------------------ Класс Item ------------------
class Item {
    private String name;
    private double price;
    private int quantity;
    public Item(String name, double price, int quantity) {
        this.name = name; this.price = price; this.quantity = quantity;
    }
    public double getTotal() { return price * quantity; }
    public String toString() { return name + " x" + quantity + " = " + getTotal(); }
}

// ------------------ Класс GroceryBill ------------------
class GroceryBill {
    protected List<Item> items = new ArrayList<>();
    public void addItem(Item item) { items.add(item); }
    public double calculateTotal() {
        double sum = 0;
        for (Item i : items) sum += i.getTotal();
        return sum;
    }
}

// ------------------ Класс DiscountBill ------------------
class DiscountBill extends GroceryBill {
    private double discount = 0;
    public void setDiscount(double discount) { this.discount = discount; }
    @Override
    public double calculateTotal() {
        double total = super.calculateTotal();
        return total - total * discount / 100;
    }
}

// ------------------ Классы банковских счетов ------------------
abstract class BankingAccount {
    protected String owner;
    protected double balance;
    public BankingAccount(String owner, double balance) {
        this.owner = owner; this.balance = balance;
    }
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
    public double getBalance() { return balance; }
}

class Debit extends BankingAccount {
    public Debit(String owner, double balance) { super(owner, balance); }
    public void deposit(double amount) { balance += amount; }
    public void withdraw(double amount) { if(balance >= amount) balance -= amount; }
}

class Credit extends BankingAccount {
    public Credit(String owner, double balance) { super(owner, balance); }
    public void deposit(double amount) { balance += amount; }
    public void withdraw(double amount) { balance -= amount; } // можно в минус
}

// ------------------ Класс Employee ------------------
class Employee {
    private String name;
    private String position;
    private double salary;
    public Employee(String name, String position, double salary) {
        this.name = name; this.position = position; this.salary = salary;
    }
    public String toString() {
        return "Employee: " + name + ", Position: " + position + ", Salary: " + salary;
    }
}

// ------------------ Класс Startup ------------------
class Startup {
    private List<BankingAccount> accounts = new ArrayList<>();
    public void addAccount(BankingAccount account) { accounts.add(account); }
    public double getTotalBalance() {
        double sum = 0;
        for(BankingAccount acc : accounts) sum += acc.getBalance();
        return sum;
    }
}

// ------------------ Main ------------------
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\nВыберите задание (1-6) или 0 для выхода:");
            int choice = sc.nextInt();
            sc.nextLine(); // очистка буфера

            switch(choice) {
                case 1: // Point
                    System.out.print("Введите координаты точки 1 (x y): ");
                    double x1 = sc.nextDouble();
                    double y1 = sc.nextDouble();
                    System.out.print("Введите координаты точки 2 (x y): ");
                    double x2 = sc.nextDouble();
                    double y2 = sc.nextDouble();
                    Point p1 = new Point(x1, y1);
                    Point p2 = new Point(x2, y2);
                    System.out.println("Расстояние между точками: " + p1.distance(p2));
                    break;

                case 2: // GroceryBill
                    GroceryBill bill = new GroceryBill();
                    System.out.print("Сколько товаров добавить? ");
                    int nItems = sc.nextInt();
                    sc.nextLine();
                    for(int i=0;i<nItems;i++){
                        System.out.print("Название товара: ");
                        String name = sc.nextLine();
                        System.out.print("Цена: ");
                        double price = sc.nextDouble();
                        System.out.print("Количество: ");
                        int qty = sc.nextInt();
                        sc.nextLine();
                        bill.addItem(new Item(name, price, qty));
                    }
                    System.out.println("Сумма чека: " + bill.calculateTotal());
                    break;

                case 3: // DiscountBill
                    DiscountBill discountBill = new DiscountBill();
                    System.out.print("Сколько товаров добавить? ");
                    int mItems = sc.nextInt();
                    sc.nextLine();
                    for(int i=0;i<mItems;i++){
                        System.out.print("Название товара: ");
                        String name = sc.nextLine();
                        System.out.print("Цена: ");
                        double price = sc.nextDouble();
                        System.out.print("Количество: ");
                        int qty = sc.nextInt();
                        sc.nextLine();
                        discountBill.addItem(new Item(name, price, qty));
                    }
                    System.out.print("Введите скидку (%): ");
                    double discount = sc.nextDouble();
                    sc.nextLine();
                    discountBill.setDiscount(discount);
                    System.out.println("Сумма с учётом скидки: " + discountBill.calculateTotal());
                    break;

                case 4: // BankingAccount
                    System.out.print("Введите имя владельца дебетового счета: ");
                    String dName = sc.nextLine();
                    System.out.print("Введите баланс дебетового счета: ");
                    double dBal = sc.nextDouble();
                    sc.nextLine();
                    Debit debitAccount = new Debit(dName, dBal);

                    System.out.print("Введите имя владельца кредитного счета: ");
                    String cName = sc.nextLine();
                    System.out.print("Введите баланс кредитного счета: ");
                    double cBal = sc.nextDouble();
                    sc.nextLine();
                    Credit creditAccount = new Credit(cName, cBal);

                    System.out.print("Сумма пополнения дебетового счета: ");
                    double dep = sc.nextDouble();
                    debitAccount.deposit(dep);

                    System.out.print("Сумма снятия с кредитного счета: ");
                    double wdr = sc.nextDouble();
                    creditAccount.withdraw(wdr);

                    System.out.println("Баланс дебетового счета: " + debitAccount.getBalance());
                    System.out.println("Баланс кредитного счета: " + creditAccount.getBalance());
                    break;

                case 5: // Employee
                    System.out.print("Имя сотрудника: ");
                    String empName = sc.nextLine();
                    System.out.print("Должность: ");
                    String position = sc.nextLine();
                    System.out.print("Зарплата: ");
                    double salary = sc.nextDouble();
                    sc.nextLine();
                    Employee emp = new Employee(empName, position, salary);
                    System.out.println(emp);
                    break;

                case 6: // Startup
                    Startup startup = new Startup();
                    System.out.print("Сколько счетов добавить в стартап? ");
                    int accCount = sc.nextInt();
                    sc.nextLine();
                    for(int i=0;i<accCount;i++){
                        System.out.print("Тип счета (debit/credit): ");
                        String type = sc.nextLine().toLowerCase();
                        System.out.print("Имя владельца: ");
                        String owner = sc.nextLine();
                        System.out.print("Баланс: ");
                        double bal = sc.nextDouble();
                        sc.nextLine();
                        if(type.equals("debit")) startup.addAccount(new Debit(owner, bal));
                        else startup.addAccount(new Credit(owner, bal));
                    }
                    System.out.println("Общий баланс всех счетов: " + startup.getTotalBalance());
                    break;

                case 0:
                    System.out.println("Выход...");
                    sc.close();
                    return;

                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }
}
