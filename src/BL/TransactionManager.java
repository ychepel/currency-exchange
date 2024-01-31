package BL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import DL.Transaction;
import DL.TransactionHistoryHandler;
import DL.Currency;

public class TransactionManager {
    // Определяет приватное поле dataHistory, предназначенное для хранения экземпляра класса TransactionHistoryHandler
    private static final String HISTORY_FILE_PATH = "src/history.csv";
    private final TransactionHistoryHandler dataHistory;


    // Конструктор класса
    public TransactionManager() {
        this.dataHistory = new TransactionHistoryHandler(HISTORY_FILE_PATH);
    }

    /**
     * Метод addTransaction - добавляет новую транзакцию в историю транзакций.
     * Параметры метода: InitialCurrencyAmount - сумма обмена, initialCurrency начальная валюта, resultCurrency конечная валюта
     * использует метод read() класса TransactionHistoryHandler.
     **/
    public void addTransaction(double initialCurrencyAmount, Currency initialCurrency, Currency resultCurrency, double rate) {
        // Формируем ArrayList из транзакций из истории
        ArrayList<Transaction> data = this.dataHistory.read();
        // Создаем новую транзакцию
        Transaction newTransaction = new Transaction(
                LocalDateTime.now(),
                initialCurrencyAmount,
                initialCurrency,
                resultCurrency,
                rate
        );
        // Добавляем новую транзакцию в историю
        data.add(newTransaction);
        this.dataHistory.append(newTransaction);
    }


    /**
     * Метод getTransactions - возвращает список транзакций в заданном временном диапазоне.
     * Параметры метода: startDate - начала периода, endDate - окончание периода
     * использует метод getDateTime() класса Transaction.
     **/

    public ArrayList<Transaction> getTransactions(LocalDate startDate, LocalDate endDate) {
        // Формируем ArrayList из транзакций из истории
        ArrayList<Transaction> allTransactions = this.dataHistory.read();
        return allTransactions.stream()
                .filter(transaction ->
                        !transaction.getDateTime().isBefore(startDate.atStartOfDay())
                                && !transaction.getDateTime().plusDays(1).isAfter(endDate.atStartOfDay()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Transaction> getTransactions() {
        // Формируем ArrayList из транзакций из истории
        ArrayList<Transaction> allTransactions = this.dataHistory.read();
        return allTransactions;
    }
}
