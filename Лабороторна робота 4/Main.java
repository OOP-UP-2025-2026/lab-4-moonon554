package ua.opnu;

import java.util.*;

// ------------------ Клас Точка ------------------
public class Point {
    private double x, y;

    public Point(double x, double y) {
        if (x < 0 || y < 0) { this.x = 0; this.y = 0; }
        else { this.x = x; this.y = y; }
    }

    public double distance(Point other) {
        return Math.sqrt(Math.pow(x - other.x,2) + Math.pow(y - other.y,2));
    }

    public double getX() { return x; }
    public double getY() { return y; }

    @Override
    public String toString() { return "(" + x + ", " + y + ")"; }
}

// ------------------ Клас Товар ------------------
class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price >= 0 ? price : 0;
        this.quantity = quantity >= 0 ? quantity : 0;
    }

    public double getTotal() { return price * quantity; }

    @Override
    public String toString() {
        return name + " x" + quantity + " = " + getTotal();
    }
}

// ------------------ Клас Чек ------------------
class GroceryBill {
    protected List<Item> items = new ArrayList<>();

    public void addItem(Item item) { items.add(item); }

    public double calculateTotal() {
        double sum = 0;
        for (Item i : items) sum += i.getTotal();
        return sum;
    }
}

// ------------------ Клас Чек зі знижкою ------------------
class DiscountBill extends GroceryBill {
    private double discount = 0;

    public void setDiscount(double discount) { this.discount = discount >=0 ? discount : 0; }

    @Override
    public double calculateTotal() {
        double total = super.calculateTotal();
        return total - total * discount / 100;
    }
}

// ------------------ Банківські рахунки ------------------
abstract class BankingAccount {
    protected String owner;
    protected double balance;

    public BankingAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance >=0 ? balance : 0;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    public double getBalance() { return balance; }
}

class Debit extends BankingAccount {
    public Debit(String owner, double balance) { super(owner, balance); }

    @Override
    public void deposit(double amount) { if(amount > 0) balance += amount; }

    @Override
    public void withdraw(double amount) { if(amount > 0 && balance >= amount) balance -= amount; }
}

class Credit extends BankingAccount {
    public Credit(String owner, double balance) { super(owner, balance); }

    @Override
    public void deposit(double amount) { if(amount > 0) balance += amount; }

    @Override
    public void withdraw(double amount) { if(amount > 0) balance -= amount; }
}

// ------------------ Клас Працівник ------------------
class Employee {
    private String name;
    private String position;
    private double salary;

    public Employee(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary >=0 ? salary : 0;
    }

    @Override
    public String toString() {
        return "Працівник: " + name + ", Посада: " + position + ", Зарплата: " + salary;
    }
}

// ------------------ Клас Стартап ------------------
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
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\nОберіть завдання (1-6) або 0 для виходу:");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    System.out.print("Введіть координати точки 1 (x y): ");
                    double x1 = sc.nextDouble();
                    double y1 = sc.nextDouble();
                    System.out.print("Введіть координати точки 2 (x y): ");
                    double x2 = sc.nextDouble();
                    double y2 = sc.nextDouble();
                    Point p1 = new Point(x1, y1);
                    Point p2 = new Point(x2, y2);
                    System.out.println("Відстань між точками: " + p1.distance(p2));
                    break;

                case 2:
                    GroceryBill bill = new GroceryBill();
                    System.out.print("Скільки товарів додати? ");
                    int nItems = sc.nextInt();
                    sc.nextLine();
                    for(int i=0;i<nItems;i++){
                        System.out.print("Назва товару: ");
                        String name = sc.nextLine();
                        System.out.print("Ціна: ");
                        double price = sc.nextDouble();
                        System.out.print("Кількість: ");
                        int qty = sc.nextInt();
                        sc.nextLine();
                        bill.addItem(new Item(name, price, qty));
                    }
                    System.out.println("Сума чеку: " + bill.calculateTotal());
                    break;

                case 3:
                    DiscountBill discountBill = new DiscountBill();
                    System.out.print("Скільки товарів додати? ");
                    int mItems = sc.nextInt();
                    sc.nextLine();
                    for(int i=0;i<mItems;i++){
                        System.out.print("Назва товару: ");
                        String name = sc.nextLine();
                        System.out.print("Ціна: ");
                        double price = sc.nextDouble();
                        System.out.print("Кількість: ");
                        int qty = sc.nextInt();
                        sc.nextLine();
                        discountBill.addItem(new Item(name, price, qty));
                    }
                    System.out.print("Введіть знижку (%): ");
                    double discount = sc.nextDouble();
                    sc.nextLine();
                    discountBill.setDiscount(discount);
                    System.out.println("Сума з урахуванням знижки: " + discountBill.calculateTotal());
                    break;

                case 4:
                    System.out.print("Ім'я власника дебетового рахунку: ");
                    String dName = sc.nextLine();
                    System.out.print("Баланс дебетового рахунку: ");
                    double dBal = sc.nextDouble();
                    sc.nextLine();
                    Debit debitAccount = new Debit(dName, dBal);

                    System.out.print("Ім'я власника кредитного рахунку: ");
                    String cName = sc.nextLine();
                    System.out.print("Баланс кредитного рахунку: ");
                    double cBal = sc.nextDouble();
                    sc.nextLine();
                    Credit creditAccount = new Credit(cName, cBal);

                    System.out.print("Сума поповнення дебетового рахунку: ");
                    double dep = sc.nextDouble();
                    debitAccount.deposit(dep);

                    System.out.print("Сума зняття з кредитного рахунку: ");
                    double wdr = sc.nextDouble();
                    creditAccount.withdraw(wdr);

                    System.out.println("Баланс дебетового рахунку: " + debitAccount.getBalance());
                    System.out.println("Баланс кредитного рахунку: " + creditAccount.getBalance());
                    break;

                case 5:
                    System.out.print("Ім'я працівника: ");
                    String empName = sc.nextLine();
                    System.out.print("Посада: ");
                    String position = sc.nextLine();
                    System.out.print("Зарплата: ");
                    double salary = sc.nextDouble();
                    sc.nextLine();
                    Employee emp = new Employee(empName, position, salary);
                    System.out.println(emp);
                    break;

                case 6:
                    Startup startup = new Startup();
                    System.out.print("Скільки рахунків додати у стартап? ");
                    int accCount = sc.nextInt();
                    sc.nextLine();
                    for(int i=0;i<accCount;i++){
                        System.out.print("Тип рахунку (debit/credit): ");
                        String type = sc.nextLine().toLowerCase();
                        System.out.print("Ім'я власника: ");
                        String owner = sc.nextLine();
                        System.out.print("Баланс: ");
                        double bal = sc.nextDouble();
                        sc.nextLine();
                        if(type.equals("debit")) startup.addAccount(new Debit(owner, bal));
                        else startup.addAccount(new Credit(owner, bal));
                    }
                    System.out.println("Загальний баланс усіх рахунків: " + startup.getTotalBalance());
                    break;

                case 0:
                    System.out.println("Вихід...");
                    sc.close();
                    return;

                default:
                    System.out.println("Невірний вибір!");
            }
        }
    }
}

