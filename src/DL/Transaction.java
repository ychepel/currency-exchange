package DL;

import java.time.LocalDateTime;

public class Transaction {
    private LocalDateTime dateTime;
    private double initialCurrencyAmount;
    private Currency initialCurrency;
    private Currency resultCurrency;
    private double exchangeRate;


    public Transaction(LocalDateTime dateTime, double initialCurrencyAmount, Currency initialCurrency, Currency resultCurrency, double exchangeRate) {
        this.dateTime = dateTime;
        this.initialCurrencyAmount = initialCurrencyAmount;
        this.initialCurrency = initialCurrency;
        this.resultCurrency = resultCurrency;
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

    public Currency getInitialCurrencyTitle() {
        return initialCurrency;
    }

    public void setInitialCurrencyTitle(Currency initialCurrency) {
        this.initialCurrency = initialCurrency;
    }

    public Currency getResultCurrencyTitle() {
        return resultCurrency;
    }

    public void setResultCurrencyTitle(Currency resultCurrency) {
        this.resultCurrency = resultCurrency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "dateTime=" + dateTime +
                ", InitialCurrencyAmount=" + initialCurrencyAmount +
                ", InitialCurrencyTitle='" + initialCurrency + '\'' +
                ", ResultCurrencyTitle='" + resultCurrency + '\'' +
                ", ExchangeRate=" + exchangeRate +
                '}';
    }
}
