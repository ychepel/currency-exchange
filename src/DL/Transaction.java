package DL;

import java.time.LocalDate;

public class Transaction {
    private LocalDate deadLine;
    private double InitialCurrencyAmount;
    private String InitialCurrencyTitle;
    private String ResultCurrencyTitle;
    private double ExchangeRate;


    public Transaction(LocalDate deadLine, double initialCurrencyAmount, String initialCurrencyTitle, String resultCurrencyTitle, double exchangeRate) {
        this.deadLine = deadLine;
        InitialCurrencyAmount = initialCurrencyAmount;
        InitialCurrencyTitle = initialCurrencyTitle;
        ResultCurrencyTitle = resultCurrencyTitle;
        ExchangeRate = exchangeRate;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public double getInitialCurrencyAmount() {
        return InitialCurrencyAmount;
    }

    public void setInitialCurrencyAmount(double initialCurrencyAmount) {
        InitialCurrencyAmount = initialCurrencyAmount;
    }

    public String getInitialCurrencyTitle() {
        return InitialCurrencyTitle;
    }

    public void setInitialCurrencyTitle(String initialCurrencyTitle) {
        InitialCurrencyTitle = initialCurrencyTitle;
    }

    public String getResultCurrencyTitle() {
        return ResultCurrencyTitle;
    }

    public void setResultCurrencyTitle(String resultCurrencyTitle) {
        ResultCurrencyTitle = resultCurrencyTitle;
    }

    public double getExchangeRate() {
        return ExchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        ExchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "deadLine=" + deadLine +
                ", InitialCurrencyAmount=" + InitialCurrencyAmount +
                ", InitialCurrencyTitle='" + InitialCurrencyTitle + '\'' +
                ", ResultCurrencyTitle='" + ResultCurrencyTitle + '\'' +
                ", ExchangeRate=" + ExchangeRate +
                '}';
    }
}
