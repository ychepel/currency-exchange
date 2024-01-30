package UI;

import BL.CurrencyManager;
import BL.TransactionManager;
import DL.CurrencyTitle;
import DL.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ConsoleUI {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        printHeader();
        while (true) {
            System.out.println("Select an option: ");
            System.out.println("1. Change currency");
            System.out.println("2. View history");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    exchangeCurrency();
                    break;
                case 2:
                    showHistory();
                    break;
                case 0:
                    return;
            }
        }
    }

    private static void printHeader() {
        System.out.println("\n" +
                " __        __   __   ___       __          ___      __                  __   ___ \n" +
                "/  ` |  | |__) |__) |__  |\\ | /  ` \\ /    |__  \\_/ /  ` |__|  /\\  |\\ | / _` |__  \n" +
                "\\__, \\__/ |  \\ |  \\ |___ | \\| \\__,  |     |___ / \\ \\__, |  | /~~\\ | \\| \\__> |___ \n" +
                "                                                                                 \n");
    }

    private static void exchangeCurrency() {
        CurrencyManager currencyManager = new CurrencyManager();

        LocalDate currentDate = LocalDate.now();

        System.out.printf("Today's %s. exchange rates:  %s.%n ",
                currentDate.format(dateFormat),
                currencyManager.getAvailableCurrencies()
        );

        System.out.print("Enter the currency for exchange: ");
        String fromCurrencyString = scanner.nextLine().toUpperCase();
        CurrencyTitle fromCurrency = CurrencyTitle.valueOf(fromCurrencyString);

        System.out.print("Enter the amount for exchanged: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter the currency to acquire: ");
        String toCurrencyString = scanner.nextLine().toUpperCase();
        CurrencyTitle toCurrency = CurrencyTitle.valueOf(toCurrencyString);

        double rate = currencyManager.calculateRate(fromCurrency, toCurrency);
        double resultAmount = amount * rate;

        System.out.printf("Exchange %.2f %s to %.2f %s at %.4f rate %s? (Y/N)%n ",
                amount,
                fromCurrency,
                resultAmount,
                toCurrency,
                rate
        );
        String answer = scanner.nextLine().toLowerCase();
        if ("y".equals(answer)) {
            TransactionManager transactionManager = new TransactionManager();
            transactionManager.addTransaction(amount, fromCurrency, toCurrency, rate);
            System.out.println("Exchange was successful");
        } else {
            System.out.println("Exchange canceled");
        }
    }

    private static void showHistory() {
        TransactionManager transactionManager = new TransactionManager();
        while (true) {
            System.out.println("Select an option: ");
            System.out.println("1. All transaction");
            System.out.println("2. For the period");
            System.out.println("0. Return to previous menu");
            int choice = scanner.nextInt();
            scanner.nextLine();
            ArrayList<Transaction> transactions = new ArrayList<>();
            switch (choice) {
                case 1:
                    transactions = getHistoryAll(transactionManager);
                    break;
                case 2:
                    transactions = getHistoryFiltered(transactionManager);
                    break;
                case 0:
                    return;
            }
            if (!transactions.isEmpty()) {
                System.out.println("Transactions history:");
                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }
            }
        }
    }

    private static ArrayList<Transaction> getHistoryAll(TransactionManager transactionManager) {
        return transactionManager.getTransactions();
    }

    private static ArrayList<Transaction> getHistoryFiltered(TransactionManager transactionManager) {
        System.out.println("Enter start date (format: dd.MM.yyyy: )");
        String startDate = scanner.nextLine();
        System.out.println("Enter end date (format: dd.MM.yyyy: )");
        String endDate = scanner.nextLine();
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            transactions = transactionManager.getTransactions(
                    LocalDate.parse(startDate, dateFormat),
                    LocalDate.parse(endDate, dateFormat)
            );
        } catch (DateTimeParseException exception) {
            System.err.println("Incorrect data entered.");
        }
        return transactions;
    }
}
