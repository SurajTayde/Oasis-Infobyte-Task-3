import java.util.ArrayList;

public class User {
    private String userId;
    
    private String pin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        balance -= amount;
        transactionHistory.add("Withdrew: " + amount);
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }
}
