package com.pluralsight;

public class Transactions {
}import java.io.*;
        import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static final String FILE_NAME = "transactions.csv";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        ensureFileExists();

        System.out.println("=========================================");
        System.out.println(" Welcome to Personal Accounting Ledger   ");
        System.out.println("=========================================");

        while (running) {
            System.out.println("\n--- HOME SCREEN ---");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    addTransaction(scanner, true);
                    break;
                case "P":
                    addTransaction(scanner, false);
                    break;
                case "L":
                    showLedgerMenu(scanner);
                    break;
                case "X":
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void ensureFileExists() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating transactions file: " + e.getMessage());
            }
        }
    }

    private static void addTransaction(Scanner scanner, boolean isDeposit) {
        System.out.println("\n--- " + (isDeposit ? "ADD DEPOSIT" : "MAKE PAYMENT (DEBIT)") + " ---");

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) description = "N/A";

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine().trim();
        if (vendor.isEmpty()) vendor = "N/A";

        double amount = 0;
        while (true) {
            System.out.print("Enter amount ($): ");
            try {
                amount = Double.parseDouble(scanner.nextLine().trim());
                if (amount <= 0) {
                    System.out.println("Amount must be greater than zero.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid amount.");
            }
        }

        if (!isDeposit) {
            amount = -amount; // Payments are negative
        }

        Transaction transaction = new Transaction(date, time, description, vendor, amount);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(transaction.toCsvString());
            bw.newLine();
            System.out.println("Transaction successfully saved!");
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    private static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return transactions;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    LocalDate date = LocalDate.parse(parts[0], DATE_FORMATTER);
                    LocalTime time = LocalTime.parse(parts[1], TIME_FORMATTER);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }

        // Sort newest first
        transactions.sort((t1, t2) -> {
            int dateComp = t2.getDate().compareTo(t1.getDate());
            if (dateComp != 0) return dateComp;
            return t2.getTime().compareTo(t1.getTime());
        });

        return transactions;
    }

    private static void showLedgerMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- LEDGER SCREEN ---");
            System.out.println("A) All - Display all entries");
            System.out.println("D) Deposits - Display only deposits");
            System.out.println("P) Payments - Display only payments");
            System.out.println("R) Reports - Run pre-defined or custom reports");
            System.out.println("H) Home - Go back to home page");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim().toUpperCase();
            List<Transaction> transactions = loadTransactions();

            switch (choice) {
                case "A":
                    displayTransactions(transactions, "All Transactions");
                    break;
                case "D":
                    List<Transaction> deposits = new ArrayList<>();
                    for (Transaction t : transactions) {
                        if (t.getAmount() > 0) deposits.add(t);
                    }
                    displayTransactions(deposits, "Deposits");
                    break;
                case "P":
                    List<Transaction> payments = new ArrayList<>();
                    for (Transaction t : transactions) {
                        if (t.getAmount() < 0) payments.add(t);
                    }
                    displayTransactions(payments, "Payments");
                    break;
                case "R":
                    showReportsMenu(scanner, transactions);
                    break;
                case "H":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayTransactions(List<Transaction> transactions, String title) {
        System.out.println("\n--- " + title.toUpperCase() + " ---");
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        System.printf("%-12s | %-10s | %-25s | %-15s | %10s\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("----------------------------------------------------------------------------------");
        for (Transaction t : transactions) {
            System.out.printf("%-12s | %-10s | %-25s | %-15s | $%9.2f\n",
                    t.getDate(), t.getTime(),
                    t.getDescription().length() > 25 ? t.getDescription().substring(0, 22) + "..." : t.getDescription(),
                    t.getVendor().length() > 15 ? t.getVendor().substring(0, 12) + "..." : t.getVendor(),
                    t.getAmount());
        }
    }

    private static void showReportsMenu(Scanner scanner, List<Transaction> allTransactions) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- REPORTS MENU ---");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("0) Back to Ledger Menu");
            System.out.print("Choose a report option: ");

            String choice = scanner.nextLine().trim();
            LocalDate now = LocalDate.now();

            switch (choice) {
                case "1": {
                    LocalDate start = now.withDayOfMonth(1);
                    List<Transaction> filtered = new ArrayList<>();
                    for (Transaction t : allTransactions) {
                        if (!t.getDate().isBefore(start) && !t.getDate().isAfter(now)) {
                            filtered.add(t);
                        }
                    }
                    displayTransactions(filtered, "Month To Date");
                    break;
                }
                case "2": {
                    LocalDate firstOfThisMonth = now.withDayOfMonth(1);
                    LocalDate startPrev = firstOfThisMonth.minusMonths(1);
                    LocalDate endPrev = firstOfThisMonth.minusDays(1);
                    List<Transaction> filtered = new ArrayList<>();
                    for (Transaction t : allTransactions) {
                        if (!t.getDate().isBefore(startPrev) && !t.getDate().isAfter(endPrev)) {
                            filtered.add(t);
                        }
                    }
                    displayTransactions(filtered, "Previous Month");
                    break;
                }
                case "3": {
                    LocalDate start = now.withDayOfYear(1);
                    List<Transaction> filtered = new ArrayList<>();
                    for (Transaction t : allTransactions) {
                        if (!t.getDate().isBefore(start) && !t.getDate().isAfter(now)) {
                            filtered.add(t);
                        }
                    }
                    displayTransactions(filtered, "Year To Date");
                    break;
                }
                case "4": {
                    int prevYear = now.getYear() - 1;
                    LocalDate start = LocalDate.of(prevYear, 1, 1);
                    LocalDate end = LocalDate.of(prevYear, 12, 31);
                    List<Transaction> filtered = new ArrayList<>();
                    for (Transaction t : allTransactions) {
                        if (!t.getDate().isBefore(start) && !t.getDate().isAfter(end)) {
                            filtered.add(t);
                        }
                    }
                    displayTransactions(filtered, "Previous Year (" + prevYear + ")");
                    break;
                }
                case "5": {
                    System.out.print("Enter vendor name to search: ");
                    String vendorQuery = scanner.nextLine().trim().toLowerCase();
                    List<Transaction> filtered = new ArrayList<>();
                    for (Transaction t : allTransactions) {
                        if (t.getVendor().toLowerCase().contains(vendorQuery)) {
                            filtered.add(t);
                        }
                    }
                    displayTransactions(filtered, "Vendor Search: " + vendorQuery);
                    break;
                }
                case "6": {
                    runCustomSearch(scanner, allTransactions);
                    break;
                }
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void runCustomSearch(Scanner scanner, List<Transaction> allTransactions) {
        System.out.println("\n--- CUSTOM SEARCH (Leave blank to skip any field) ---");

        System.out.print("Start Date (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine().trim();
        LocalDate startDate = startDateStr.isEmpty() ? null : LocalDate.parse(startDateStr, DATE_FORMATTER);

        System.out.print("End Date (yyyy-MM-dd): ");
        String endDateStr = scanner.nextLine().trim();
        LocalDate endDate = endDateStr.isEmpty() ? null : LocalDate.parse(endDateStr, DATE_FORMATTER);

        System.out.print("Description contains: ");
        String descQuery = scanner.nextLine().trim().toLowerCase();

        System.out.print("Vendor contains: ");
        String vendorQuery = scanner.nextLine().trim().toLowerCase();

        System.out.print("Amount (exact match or leave blank): ");
        String amountStr = scanner.nextLine().trim();
        Double targetAmount = amountStr.isEmpty() ? null : Double.parseDouble(amountStr);

        List<Transaction> filtered = new ArrayList<>();
        for (Transaction t : allTransactions) {
            boolean match = true;

            if (startDate != null && t.getDate().isBefore(startDate)) match = false;
            if (endDate != null && t.getDate().isAfter(endDate)) match = false;
            if (!descQuery.isEmpty() && !t.getDescription().toLowerCase().contains(descQuery)) match = false;
            if (!vendorQuery.isEmpty() && !t.getVendor().toLowerCase().contains(vendorQuery)) match = false;
            if (targetAmount != null && Math.abs(t.getAmount() - targetAmount) > 0.001) match = false;

            if (match) {
                filtered.add(t);
            }
        }

        displayTransactions(filtered, "Custom Search Results");
    }
}
