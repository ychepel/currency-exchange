package DL;

import java.time.LocalDate;

public class Transaction {
    private LocalDate deadLine;
    private double initialCurrencyAmount;
    private CurrencyTitle initialCurrencyTitle;
    private CurrencyTitle resultCurrencyTitle;
    private double exchangeRate;


    public Transaction(LocalDate deadLine, double initialCurrencyAmount, CurrencyTitle initialCurrencyTitle, CurrencyTitle resultCurrencyTitle, double exchangeRate) {
        this.deadLine = deadLine;
        this.initialCurrencyAmount = initialCurrencyAmount;
        this.initialCurrencyTitle = initialCurrencyTitle;
        this.resultCurrencyTitle = resultCurrencyTitle;
        this.exchangeRate = exchangeRate;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
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
        return "Transaction{" +
                "deadLine=" + deadLine +
                ", InitialCurrencyAmount=" + initialCurrencyAmount +
                ", InitialCurrencyTitle='" + initialCurrencyTitle + '\'' +
                ", ResultCurrencyTitle='" + resultCurrencyTitle + '\'' +
                ", ExchangeRate=" + exchangeRate +
                '}';
    }
}
