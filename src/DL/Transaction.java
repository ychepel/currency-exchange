package DL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDateTime dateTime;
    private double initialCurrencyAmount;
    private CurrencyTitle initialCurrencyTitle;
    private CurrencyTitle resultCurrencyTitle;
    private double exchangeRate;


    public Transaction(LocalDateTime dateTime, double initialCurrencyAmount, CurrencyTitle initialCurrencyTitle, CurrencyTitle resultCurrencyTitle, double exchangeRate) {
        this.dateTime = dateTime;
        this.initialCurrencyAmount = initialCurrencyAmount;
        this.initialCurrencyTitle = initialCurrencyTitle;
        this.resultCurrencyTitle = resultCurrencyTitle;
        this.exchangeRate = exchangeRate;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getInitialCurrencyAmount() {
        return initialCurrencyAmount;
    }

    public void setInitialCurrencyAmount(double initialCurrencyAmount) {
        this.initialCurrencyAmount = initialCurrencyAmount;
    }

    public CurrencyTitle getInitialCurrencyTitle() {
        return initialCurrencyTitle;
    }

    public void setInitialCurrencyTitle(CurrencyTitle initialCurrencyTitle) {
        this.initialCurrencyTitle = initialCurrencyTitle;
    }

    public CurrencyTitle getResultCurrencyTitle() {
        return resultCurrencyTitle;
    }

    public void setResultCurrencyTitle(CurrencyTitle resultCurrencyTitle) {
        this.resultCurrencyTitle = resultCurrencyTitle;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return "Transaction details {" +
                "date: " + dateTime.format(dateTimeFormatter) +
                ", amount: " + initialCurrencyAmount +
                ", currency for exchange: '" + initialCurrencyTitle + '\'' +
                ", required currency: '" + resultCurrencyTitle + '\'' +
                ", exchange Rate: " + exchangeRate +
                '}';
    }
}
