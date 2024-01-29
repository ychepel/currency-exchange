package BL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import DL.CurrencyExchangeHandler;
import DL.TransactionHistoryHandler;
import DL.CurrencyTitle;

public class TransactionManager {
    // Определяет приватное поле dataHistory, предназначенное для хранения экземпляра класса TransactionHistoryHandler
    private static final String HISTORY_FILE_PATH = "src/history.csv";
    private final TransactionHistoryHandler dataHistory;
    private final CurrencyManager exchangeRate;


    // Конструктор класса
    public TransactionManager() {
        this.dataHistory = new TransactionHistoryHandler(HISTORY_FILE_PATH);
        this.exchangeRate = new CurrencyManager();

    }

    /**
     * Метод addTransaction - добавляет новую транзакцию в историю транзакций.
     * Параметры метода: InitialCurrencyAmount - сумма обмена, initialCurrency начальная валюта, resultCurrency  конечная валюта
     * Использует метод read() класса TransactionHistoryHandler.
     **/
    public void addTransaction(double initialCurrencyAmount, CurrencyTitle initialCurrency, CurrencyTitle resultCurrency) {
        // Формируем ArrayList из транзакций из истории
        ArrayList<Transaction> data = this.dataHistory.read();
        // Создаем новую транзакцию
        //TODO - проверить конструктор в классе Transaction
        Transaction newTransaction = new Transaction(
                LocalDateTime.now(),
                initialCurrencyAmount,
                initialCurrency,
                resultCurrency,
                exchangeRate.calculateRate(initialCurrency, resultCurrency)
        );
        // Добавляем  новую транзакцию в историю
        data.add(newTransaction);
        this.dataHistory.append(newTransaction);
    }


    /**
     * Метод getTransactions - возвращает список транзакций в заданном временном диапазоне.
     * Параметры метода: startDate - начала периода, endDate - окончание периода
     * Использует метод getDateTime() класса Transaction.
     **/
    //TODO - уточнить наличие метода getDateTime() в классе Transaction.
    public ArrayList<Transaction> getTransactions(LocalDate startDate, LocalDate endDate) {
        // Формируем ArrayList из транзакций из истории
        ArrayList<Transaction> allTransactions = this.dataHistory.read();
        // Если даты не указаны, то возвращаем весь список
        // TODO - уточнить по вызову из UI
        if (startDate == null && endDate == null){
            return allTransactions;
        // Фильтруем транзакции по заданному временному диапазону
        } else
            return allTransactions.stream()
                    .filter(transaction ->
                            !transaction.getDateTime().isBefore(startDate)
                                    && !transaction.getDateTime().isAfter(endDate))
                    .collect(Collectors.toList());
    }
}
