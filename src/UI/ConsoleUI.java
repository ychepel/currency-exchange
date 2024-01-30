package UI;

import BL.CurrencyManager;
import BL.TransactionManager;
import DL.CurrencyExchangeHandler;
import DL.CurrencyTitle;
import DL.Transaction;
import DL.TransactionHistoryHandler;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private static Scanner scanner = new Scanner(System.in);
    public static void run() {
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

    private static void exchangeCurrency() {
        System.out.println("Введите сумму для обмена: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        CurrencyManager currencyManager = new CurrencyManager(new CurrencyExchangeHandler("rates.txt"));

        System.out.println("Введите валюту, которую хотите обменять: ");
        String fromCurrency = scanner.nextLine().toUpperCase();
        System.out.println("Введите валюту, которую хотите приобрести: ");
        String toCurrency = scanner.nextLine().toUpperCase();

        double fromCurrencyRate = currencyManager.getRate(fromCurrency);
        double toCurrencyRate = currencyManager.getRate(toCurrency);

        double resultAmount = amount * fromCurrencyRate / toCurrencyRate;

        System.out.printf("Вы хотите обменять %.2f %s на %.2f %s по курсу %.4f? (да/нет) %n",
                amount, fromCurrency, resultAmount, toCurrency, fromCurrencyRate / toCurrencyRate
        );
        String answer = scanner.nextLine().toLowerCase();
        if (answer.equals("да")) {
            Transaction transaction = new Transaction(LocalDate.now(), amount, fromCurrency, toCurrency, fromCurrencyRate / toCurrencyRate);
            TransactionManager.getInstance().addTransaction(transaction);

            System.out.println("Обмен успешно выполнен");
        } else {
            System.out.println("Обмен отменен");
        }
    }

    private static void showHistory() {
        TransactionManager transactionManager = TransactionManager.getInstance();

        System.out.println("Хотите ли посмотреть всю историю или за период? (всю/период) ");
        String answer = scanner.nextLine().toLowerCase();
        if (answer.equals("всю")) {
            List<Transaction> transactions = transactionManager.getAllTransactions();
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        } else if (answer.equals("период")) {
            System.out.println("Введите начальную дату в формате dd.MM.yyyy: ");
            String startDate = scanner.nextLine();
            System.out.println("Введите конечную дату в формате dd.MM.yyyy: ");
            String endDate = scanner.nextLine();
            
            List<Transaction> transactions = transactionManager.getTransactionsByPeriod(LocalDate.parse(startDate), LocalDate.parse(endDate));
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Неверный выбор");
        }
    }
}
