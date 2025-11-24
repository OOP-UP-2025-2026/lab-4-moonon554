package ua.opnu;

import java.util.*;

class Employee {
    String name;
    public Employee(String name) { this.name = name; }
}

class Item {
    String name;
    double price;
    double discount;
    public Item(String name, double price, double discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }
}

class GroceryBill {
    protected List<Item> items = new ArrayList<>();
    public void add(String name, double price, double discount) {
        items.add(new Item(name, price, discount));
    }
    public double getTotal() {
        double sum = 0;
        for (Item it : items) sum += it.price;
        return sum;
    }
}

class DiscountBill extends GroceryBill {
    private boolean regularCustomer;
    private int discountCount;
    private double discountAmount;

    public DiscountBill(Employee clerk, boolean regularCustomer) {
        this.regularCustomer = regularCustomer;
    }

    @Override
    public void add(String name, double price, double discount) {
        super.add(name, price, discount);
        if (regularCustomer && discount > 0) {
            discountCount++;
            discountAmount += discount;
        }
    }

    @Override
    public double getTotal() {
        double total = 0;
        for (Item it : items) {
            total += regularCustomer ? it.price - it.discount : it.price;
        }
        return total;
    }

    public int getDiscountCount() { return discountCount; }
    public double getDiscountAmount() { return discountAmount; }
    public double getDiscountPercent() {
        double fullPrice = 0;
        for (Item it : items) fullPrice += it.price;
        if (fullPrice == 0) return 0;
        return 100 - (getTotal() * 100 / fullPrice);
    }
}

class DiscountBill2 {
    private DiscountBill bill;
    public DiscountBill2(Employee clerk, boolean regularCustomer) {
        bill = new DiscountBill(clerk, regularCustomer);
    }
    public void add(String name, double price, double discount) { bill.add(name, price, discount); }
    public double getTotal() { return bill.getTotal(); }
    public int getDiscountCount() { return bill.getDiscountCount(); }
    public double getDiscountAmount() { return bill.getDiscountAmount(); }
    public double getDiscountPercent() { return bill.getDiscountPercent(); }
}

class Startup {
    int initialBalance;
    public Startup(int initialBalance) { this.initialBalance = initialBalance; }
}

class BankingAccount {
    protected int balance;
    public BankingAccount(Startup s) { this.balance = s.initialBalance; }
    public void credit(int amount) { balance += amount; }
    public void debit(int amount) { balance -= amount; }
    public int getBalance() { return balance; }
}

class MinMaxAccount extends BankingAccount {
    private int min;
    private int max;
    public MinMaxAccount(Startup s) {
        super(s);
        min = balance;
        max = balance;
    }
    @Override
    public void credit(int amount) {
        super.credit(amount);
        if (balance > max) max = balance;
    }
    @Override
    public void debit(int amount) {
        super.debit(amount);
        if (balance < min) min = balance;
    }
    public int getMin() { return min; }
    public int getMax() { return max; }
}

class Point {
    protected int x, y;
    public Point() { x = 0; y = 0; }
    public Point(int x, int y) { this.x = x; this.y = y; }
    public void setLocation(int x, int y) { this.x = x; this.y = y; }
    public double distanceFromOrigin() { return Math.sqrt(x*x + y*y); }
}

class Point3D extends Point {
    private int z;
    public Point3D() { super(); z = 0; }
    public Point3D(int x, int y, int z) { super(x, y); this.z = z; }
    public void setLocation(int x, int y, int z) { super.setLocation(x, y); this.z = z; }
    @Override
    public void setLocation(int x, int y) { super.setLocation(x, y); this.z = 0; }
    public int getZ() { return z; }
    public double distance(Point3D p) {
        int dx = x - p.x, dy = y - p.y, dz = z - p.z;
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }
    @Override
    public double distanceFromOrigin() { return Math.sqrt(x*x + y*y + z*z); }
}

public class Main {
    public static void main(String[] args) {
        Employee clerk = new Employee("Ivan");

        DiscountBill db = new DiscountBill(clerk, true);
        db.add("Milk", 50, 10);
        db.add("Bread", 30, 0);

        Startup s = new Startup(1000);
        MinMaxAccount acc = new MinMaxAccount(s);
        acc.credit(500);
        acc.debit(200);

        Point3D p1 = new Point3D(1, 2, 3);
        Point3D p2 = new Point3D(4, 6, 8);

        DiscountBill2 db2 = new DiscountBill2(clerk, true);
        db2.add("Cheese", 60, 15);
    }
}

