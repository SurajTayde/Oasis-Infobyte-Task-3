import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    private HashMap<String, User> users;
    private Scanner scanner;

    public ATM() {
        users = new HashMap<>();
        scanner = new Scanner(System.in);

        // Predefined users for the demo
        users.put("user1", new User("user1", "1234", 5000.0));
        users.put("user2", new User("user2", "5678", 10000.0));
    }

    public void start() {
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (authenticate(userId, pin)) {
            User user = users.get(userId);
            showMenu(user);
        } else {
            System.out.println("Invalid credentials. Exiting.");
        }
    }

    private boolean authenticate(String userId, String pin) {
        User user = users.get(userId);
        return user != null && user.getPin().equals(pin);
    }

    private void showMenu(User user) {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> showTransactionHistory(user);
                case 2 -> withdraw(user);
                case 3 -> deposit(user);
                case 4 -> transfer(user);
                case 5 -> {
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void showTransactionHistory(User user) {
        System.out.println("\nTransaction History:");
        for (String transaction : user.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    private void withdraw(User user) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount <= user.getBalance()) {
            user.withdraw(amount);
            System.out.println("Withdrawal successful. Remaining balance: " + user.getBalance());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private void deposit(User user) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        user.deposit(amount);
        System.out.println("Deposit successful. Current balance: " + user.getBalance());
    }

    private void transfer(User user) {
        System.out.print("Enter recipient User ID: ");
        String recipientId = scanner.next();
        User recipient = users.get(recipientId);

        if (recipient != null) {
            System.out.print("Enter amount to transfer: ");
            double amount = scanner.nextDouble();

            if (amount <= user.getBalance()) {
                user.withdraw(amount);
                recipient.deposit(amount);
                user.addTransaction("Transferred " + amount + " to " + recipientId);
                recipient.addTransaction("Received " + amount + " from " + user.getUserId());
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Recipient not found.");
        }
    }
}
