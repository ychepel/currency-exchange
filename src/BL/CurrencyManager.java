package BL;

import DL.CurrencyExchangeHandler;
import DL.CurrencyTitle;
import java.util.HashMap;

public class CurrencyManager {

    // Определяет приватное поле data, предназначенное для хранения экземпляра класса CurrencyExchangeHandler
    private static final String RATE_FILE_PATH = "src/rates.txt";
    private final CurrencyExchangeHandler data;

    public CurrencyManager() {
        this.data = new CurrencyExchangeHandler(this.RATE_FILE_PATH);
    }

    /**
     * Метод getAllCurrencies - возвращает все валюты и курсы их обмена по отношению к дефолтной валюте.
     * Использует метод read() класса CurrencyExchangeHandler.
     * Метод возвращает HashMap с валютами и их курсами.
    **/
     public HashMap<CurrencyTitle, Double> getAllCurrency () {
        return data.read();
    }

    /** Метод calculateRate - рассчитывает курс обмена между двумя валютами
     * Параметры метода: initialCurrency начальная валюта, resultCurrency  конечная валюта
     * Метод возвращает переменную типа double - курс обмена между заданными валютами
     **/
    public double calculateRate (CurrencyTitle initialCurrency, CurrencyTitle resultCurrency){
        HashMap<CurrencyTitle, Double> exchangeRates = data.read();
        return exchangeRates.get(resultCurrency) / exchangeRates.get(initialCurrency);
        //TODO - предусмотреть случай, когда курс = 0
    }

    /** Метод calculateTotalAmount - рассчитывает конечную сумму после обмена.
     * Параметры метода: InitialCurrencyAmount - сумма обмена, initialCurrency начальная валюта, resultCurrency конечная валюта
     * Метод возвращает переменную типа double - конечная сумма после обмена
    **/
    public double calculateTotalAmount (double InitialCurrencyAmount,CurrencyTitle initialCurrency, CurrencyTitle resultCurrency) {
        return calculateRate(initialCurrency, resultCurrency) * InitialCurrencyAmount;


    }
}
