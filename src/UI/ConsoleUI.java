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
            System.out.println("Выберите опцию: ");
            System.out.println("1. Поменять валюту");
            System.out.println("2. Посмотреть историю");
            System.out.println("0. Выход");
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

        System.out.printf("Сегодня %s. Для обмена доступны следующие валюты:  %s.%n",
                currentDate.format(dateFormat),
                currencyManager.getAvailableCurrencies()
        );

        System.out.print("Введите валюту, которую хотите обменять: ");
        String fromCurrencyString = scanner.nextLine().toUpperCase();
        CurrencyTitle fromCurrency = CurrencyTitle.valueOf(fromCurrencyString);

        System.out.print("Введите сумму для обмена: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Введите валюту, которую хотите приобрести: ");
        String toCurrencyString = scanner.nextLine().toUpperCase();
        CurrencyTitle toCurrency = CurrencyTitle.valueOf(toCurrencyString);

        double rate = currencyManager.calculateRate(fromCurrency, toCurrency);
        double resultAmount = amount * rate;

        System.out.printf("Вы хотите обменять %.2f %s на %.2f %s по курсу %.4f? (Y/N)%n",
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
            System.out.println("Обмен успешно выполнен");
        } else {
            System.out.println("Обмен отменен");
        }
    }

    private static void showHistory() {
        TransactionManager transactionManager = new TransactionManager();
        while (true) {
            System.out.println("Выберите опцию: ");
            System.out.println("1. Вся история");
            System.out.println("2. За период");
            System.out.println("0. Вернуться в предыдущее меню");
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
                System.out.println("История транзакций:");
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
        System.out.println("Введите начальную дату в формате dd.MM.yyyy: ");
        String startDate = scanner.nextLine();
        System.out.println("Введите конечную дату в формате dd.MM.yyyy: ");
        String endDate = scanner.nextLine();
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            transactions = transactionManager.getTransactions(
                    LocalDate.parse(startDate, dateFormat),
                    LocalDate.parse(endDate, dateFormat)
            );
        } catch (DateTimeParseException exception) {
            System.err.println("Введены неверные данные.");
        }
        return transactions;
    }
}
