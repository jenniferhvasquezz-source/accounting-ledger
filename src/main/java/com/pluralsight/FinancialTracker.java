
package com.pluralsight;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Capstone skeleton – personal finance tracker.
 * ------------------------------------------------
 * File format  (pipe-delimited)
 *     yyyy-MM-dd|HH:mm:ss|description|vendor|amount
 * A deposit has a positive amount; a payment is stored
 * as a negative amount.
 *
 * TODO: create the Transaction class in this package first.
 *       One object = one line of the file. Decide its fields from
 *       the file format above, add a constructor and getters.
 *       This class does not compile until Transaction exists.
 */
public class FinancialTracker {

    /* ------------------------------------------------------------------
       Shared data and formatters
       ------------------------------------------------------------------ */
    private static final ArrayList<Transaction> transactions = new ArrayList<>();
    private static final String FILE_NAME = "transactions.csv";

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm:ss";
    private static final String DATETIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    /* ------------------------------------------------------------------
       Main menu
       ------------------------------------------------------------------ */
    public static void main(String[] args) {
        loadTransactions(FILE_NAME);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to TransactionApp");
            System.out.println("Choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "D" -> addDeposit(scanner);
                case "P" -> addPayment(scanner);
                case "L" -> ledgerMenu(scanner);
                case "X" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
        scanner.close();
    }

    /* ------------------------------------------------------------------
       File I/O
       ------------------------------------------------------------------ */

    /**
     * Load transactions from FILE_NAME.
     * • If the file doesn’t exist, create an empty one so that future writes succeed.
     * • Each line looks like: date|time|description|vendor|amount
     */
    public static void loadTransactions(String fileName) {
        // TODO: create file if it does not exist, then read each line,
        //       parse the five fields, build a Transaction object,
        //       and add it to the transactions list.
    }

    /* ------------------------------------------------------------------
       Add new transactions
       ------------------------------------------------------------------ */

    /**
     * Prompt for ONE date+time string in the format
     * "yyyy-MM-dd HH:mm:ss", plus description, vendor, amount.
     * Validate that the amount entered is positive.
     * Store the amount as-is (positive) and append to the file.
     */
    private static void addDeposit(Scanner scanner) {
        // TODO
    }

    /**
     * Same prompts as addDeposit.
     * Amount must be entered as a positive number,
     * then converted to a negative amount before storing.
     */
    private static void addPayment(Scanner scanner) {
        // TODO
    }

    /* ------------------------------------------------------------------
       Ledger menu
       ------------------------------------------------------------------ */
    private static void ledgerMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Ledger");
            System.out.println("Choose an option:");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A" -> displayLedger();
                case "D" -> displayDeposits();
                case "P" -> displayPayments();
                case "R" -> reportsMenu(scanner);
                case "H" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    /* ------------------------------------------------------------------
       Display helpers: show data in neat columns
       ------------------------------------------------------------------ */
    private static void displayLedger() { /* TODO – print all transactions in column format, newest first */ }

    private static void displayDeposits() { /* TODO – only amount > 0               */ }

    private static void displayPayments() { /* TODO – only amount < 0               */ }

    /* ------------------------------------------------------------------
       Reports menu
       ------------------------------------------------------------------ */
    private static void reportsMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Reports");
            System.out.println("Choose an option:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("0) Back");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> {/* TODO – month-to-date report */ }
                case "2" -> {/* TODO – previous month report */ }
                case "3" -> {/* TODO – year-to-date report   */ }
                case "4" -> {/* TODO – previous year report  */ }
                case "5" -> {/* TODO – prompt for vendor then report */ }
                case "6" -> customSearch(scanner);
                case "0" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    /* ------------------------------------------------------------------
       Reporting helpers
       ------------------------------------------------------------------ */
    private static void filterTransactionsByDate(LocalDate start, LocalDate end) {
        // TODO – iterate transactions, print those within the range
    }

    private static void filterTransactionsByVendor(String vendor) {
        // TODO – iterate transactions, print those with matching vendor
    }

    private static void customSearch(Scanner scanner) {
        // TODO – prompt for any combination of date range, description,
        //        vendor, and exact amount, then display matches
    }

    /* ------------------------------------------------------------------
       Utility parsers (you can reuse in many places)
       ------------------------------------------------------------------ */
    private static LocalDate parseDate(String s) {
        /* TODO – return LocalDate or null */
        return null;
    }

    private static Double parseDouble(String s) {
        /* TODO – return Double   or null */
        return null;
    }package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

    /*
     * Capstone skeleton – personal finance tracker.
     * ------------------------------------------------
     * File format  (pipe-delimited)
     *     yyyy-MM-dd|HH:mm:ss|description|vendor|amount
     * A deposit has a positive amount; a payment is stored
     * as a negative amount.
     *
     * TODO: create the Transaction class in this package first.
     *       One object = one line of the file. Decide its fields from
     *       the file format above, add a constructor and getters.
     *       This class does not compile until Transaction exists.
     */
    public class FinancialTracker {

        /* ------------------------------------------------------------------
           Shared data and formatters
           ------------------------------------------------------------------ */
        private static final ArrayList<Transaction> transactions = new ArrayList<>();
        private static final String FILE_NAME = "transactions.csv";

        private static final String DATE_PATTERN = "yyyy-MM-dd";
        private static final String TIME_PATTERN = "HH:mm:ss";
        private static final String DATETIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

        private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern(DATE_PATTERN);
        private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern(TIME_PATTERN);
        private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

        /* ------------------------------------------------------------------
           Main menu
           ------------------------------------------------------------------ */
        public static void main(String[] args) {
            loadTransactions(FILE_NAME);

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\nWelcome to TransactionApp");
                System.out.println("Choose an option:");
                System.out.println("D) Add Deposit");
                System.out.println("P) Make Payment (Debit)");
                System.out.println("L) Ledger");
                System.out.println("X) Exit");
                System.out.print("Enter choice: ");

                String input = scanner.nextLine().trim();

                switch (input.toUpperCase()) {
                    case "D" -> addDeposit(scanner);
                    case "P" -> addPayment(scanner);
                    case "L" -> ledgerMenu(scanner);
                    case "X" -> running = false;
                    default -> System.out.println("Invalid option");
                }
            }
            scanner.close();
        }

    /* ------------------------------------------------------------------
       File I/O
       ------------------------------------------------------------------ */

        /**
         * Load transactions from FILE_NAME.
         * • If the file doesn’t exist, create an empty one so that future writes succeed.
         * • Each line looks like: date|time|description|vendor|amount
         */
        public static void loadTransactions(String fileName) {
            File file = new File(fileName);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                    return;
                }

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    transactions.clear();
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split("\\|");
                        if (parts.length == 5) {
                            String date = parts[0];
                            String time = parts[1];
                            String description = parts[2];
                            String vendor = parts[3];
                            double amount = Double.parseDouble(parts[4]);
                            transactions.add(new Transaction(date, time, description, vendor, amount));
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading transactions file: " + e.getMessage());
            }
        }

        private static void saveTransaction(Transaction t) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                writer.write(t.toCSVString());
                writer.newLine();
                transactions.add(t);
                System.out.println("Transaction saved successfully!");
            } catch (IOException e) {
                System.out.println("Error saving transaction: " + e.getMessage());
            }
        }

    /* ------------------------------------------------------------------
       Add new transactions
       ------------------------------------------------------------------ */

        /**
         * Prompt for ONE date+time string in the format
         * "yyyy-MM-dd HH:mm:ss", plus description, vendor, amount.
         * Validate that the amount entered is positive.
         * Store the amount as-is (positive) and append to the file.
         */
        private static void addDeposit(Scanner scanner) {
            try {
                System.out.print("Enter date and time (yyyy-MM-dd HH:mm:ss) or press Enter for current time: ");
                String dateTimeInput = scanner.nextLine().trim();

                LocalDate date;
                LocalTime time;

                if (dateTimeInput.isEmpty()) {
                    date = LocalDate.now();
                    time = LocalTime.now();
                } else {
                    java.time.LocalDateTime parsedDT = java.time.LocalDateTime.parse(dateTimeInput, DATETIME_FMT);
                    date = parsedDT.toLocalDate();
                    time = parsedDT.toLocalTime();
                }

                System.out.print("Enter description: ");
                String description = scanner.nextLine().trim();

                System.out.print("Enter vendor: ");
                String vendor = scanner.nextLine().trim();

                System.out.print("Enter amount: ");
                double amount = Double.parseDouble(scanner.nextLine().trim());

                if (amount <= 0) {
                    System.out.println("Amount must be positive for a deposit.");
                    return;
                }

                Transaction t = new Transaction(date.format(DATE_FMT), time.format(TIME_FMT), description, vendor, amount);
                saveTransaction(t);

            } catch (Exception e) {
                System.out.println("Invalid input format. Please try again.");
            }
        }

        /**
         * Same prompts as addDeposit.
         * Amount must be entered as a positive number,
         * then converted to a negative amount before storing.
         */
        private static void addPayment(Scanner scanner) {
            try {
                System.out.print("Enter date and time (yyyy-MM-dd HH:mm:ss) or press Enter for current time: ");
                String dateTimeInput = scanner.nextLine().trim();

                LocalDate date;
                LocalTime time;

                if (dateTimeInput.isEmpty()) {
                    date = LocalDate.now();
                    time = LocalTime.now();
                } else {
                    java.time.LocalDateTime parsedDT = java.time.LocalDateTime.parse(dateTimeInput, DATETIME_FMT);
                    date = parsedDT.toLocalDate();
                    time = parsedDT.toLocalTime();
                }

                System.out.print("Enter description: ");
                String description = scanner.nextLine().trim();

                System.out.print("Enter vendor: ");
                String vendor = scanner.nextLine().trim();

                System.out.print("Enter amount: ");
                double amount = Double.parseDouble(scanner.nextLine().trim());

                if (amount <= 0) {
                    System.out.println("Amount must be positive (it will be saved as a payment/debit).");
                    return;
                }

                double paymentAmount = -Math.abs(amount);

                Transaction t = new Transaction(date.format(DATE_FMT), time.format(TIME_FMT), description, vendor, paymentAmount);
                saveTransaction(t);

            } catch (Exception e) {
                System.out.println("Invalid input format. Please try again.");
            }
        }

        /* ------------------------------------------------------------------
           Ledger menu
           ------------------------------------------------------------------ */
        private static void ledgerMenu(Scanner scanner) {
            boolean running = true;
            while (running) {
                System.out.println("\nLedger");
                System.out.println("Choose an option:");
                System.out.println("A) All");
                System.out.println("D) Deposits");
                System.out.println("P) Payments");
                System.out.println("R) Reports");
                System.out.println("H) Home");
                System.out.print("Enter choice: ");

                String input = scanner.nextLine().trim();

                switch (input.toUpperCase()) {
                    case "A" -> displayLedger();
                    case "D" -> displayDeposits();
                    case "P" -> displayPayments();
                    case "R" -> reportsMenu(scanner);
                    case "H" -> running = false;
                    default -> System.out.println("Invalid option");
                }
            }
        }

        /* ------------------------------------------------------------------
           Display helpers: show data in neat columns
           ------------------------------------------------------------------ */
        private static void displayTransactionsList(ArrayList<Transaction> list) {
            if (list.isEmpty()) {
                System.out.println("No transactions found.");
                return;
            }
            System.out.println("\n----------------------------------------------------------------------------------");
            System.out.printf("%-12s %-10s %-25s %-20s %11s%n", "Date", "Time", "Description", "Vendor", "Amount");
            System.out.println("----------------------------------------------------------------------------------");

            for (int i = list.size() - 1; i >= 0; i--) {
                System.out.println(list.get(i));
            }
            System.out.println("----------------------------------------------------------------------------------");
        }

        private static void displayLedger() {
            displayTransactionsList(transactions);
        }

        private static void displayDeposits() {
            ArrayList<Transaction> deposits = new ArrayList<>();
            for (Transaction t : transactions) {
                if (t.getAmount() > 0) {
                    deposits.add(t);
                }
            }
            displayTransactionsList(deposits);
        }

        private static void displayPayments() {
            ArrayList<Transaction> payments = new ArrayList<>();
            for (Transaction t : transactions) {
                if (t.getAmount() < 0) {
                    payments.add(t);
                }
            }
            displayTransactionsList(payments);
        }

        /* ------------------------------------------------------------------
           Reports menu
           ------------------------------------------------------------------ */
        private static void reportsMenu(Scanner scanner) {
            boolean running = true;
            LocalDate now = LocalDate.now();

            while (running) {
                System.out.println("\nReports");
                System.out.println("Choose an option:");
                System.out.println("1) Month To Date");
                System.out.println("2) Previous Month");
                System.out.println("3) Year To Date");
                System.out.println("4) Previous Year");
                System.out.println("5) Search by Vendor");
                System.out.println("6) Custom Search");
                System.out.println("0) Back");
                System.out.print("Enter choice: ");

                String input = scanner.nextLine().trim();

                switch (input) {
                    case "1" -> {
                        LocalDate start = now.withDayOfMonth(1);
                        filterTransactionsByDate(start, now);
                    }
                    case "2" -> {
                        LocalDate start = now.minusMonths(1).withDayOfMonth(1);
                        LocalDate end = now.minusMonths(1).withDayOfMonth(now.minusMonths(1).lengthOfMonth());
                        filterTransactionsByDate(start, end);
                    }
                    case "3" -> {
                        LocalDate start = LocalDate.of(now.getYear(), 1, 1);
                        filterTransactionsByDate(start, now);
                    }
                    case "4" -> {
                        int prevYear = now.getYear() - 1;
                        LocalDate start = LocalDate.of(prevYear, 1, 1);
                        LocalDate end = LocalDate.of(prevYear, 12, 31);
                        filterTransactionsByDate(start, end);
                    }
                    case "5" -> {
                        System.out.print("Enter vendor name to search: ");
                        String vendor = scanner.nextLine().trim();
                        filterTransactionsByVendor(vendor);
                    }
                    case "6" -> customSearch(scanner);
                    case "0" -> running = false;
                    default -> System.out.println("Invalid option");
                }
            }
        }

        /* ------------------------------------------------------------------
           Reporting helpers
           ------------------------------------------------------------------ */
        private static void filterTransactionsByDate(LocalDate start, LocalDate end) {
            ArrayList<Transaction> filtered = new ArrayList<>();
            for (Transaction t : transactions) {
                LocalDate d = parseDate(t.getDate());
                if (d != null && !d.isBefore(start) && !d.isAfter(end)) {
                    filtered.add(t);
                }
            }
            displayTransactionsList(filtered);
        }

        private static void filterTransactionsByVendor(String vendorName) {
            ArrayList<Transaction> filtered = new ArrayList<>();
            for (Transaction t : transactions) {
                if (t.getVendor().toLowerCase().contains(vendorName.toLowerCase())) {
                    filtered.add(t);
                }
            }
            displayTransactionsList(filtered);
        }

        private static void customSearch(Scanner scanner) {
            System.out.print("Enter start date (yyyy-MM-dd) or leave blank: ");
            String startDateStr = scanner.nextLine().trim();
            LocalDate startDate = startDateStr.isEmpty() ? null : parseDate(startDateStr);

            System.out.print("Enter end date (yyyy-MM-dd) or leave blank: ");
            String endDateStr = scanner.nextLine().trim();
            LocalDate endDate = endDateStr.isEmpty() ? null : parseDate(endDateStr);

            System.out.print("Enter description or leave blank: ");
            String descQuery = scanner.nextLine().trim().toLowerCase();

            System.out.print("Enter vendor or leave blank: ");
            String vendorQuery = scanner.nextLine().trim().toLowerCase();

            System.out.print("Enter amount or leave blank: ");
}
