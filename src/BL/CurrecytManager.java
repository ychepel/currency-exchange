package BL;

import java.util.HashMap;

public class CurrencyManager {
    //Определяет статическую константу - значение валюты по умолчанию
    public static final CurrencyTitle defaultCurrency = CurrencyTitle.EUR;
    // Определяет приватное поле data, предназначенное для хранения экземпляра класса CurrencyExchangeHandler
    private final CurrencyExchangeHandler data;

    // Конструктор
    public CurrentManager(String rateFilePath) {
        this.data = new CurrencyExchangeHandler(rateFilePath);
    }

    /**
     * Метод getAllCurrencies - возвращает все валюты и курсы их обмена по отношению к дефолтной валюте.
     * Использует метод read() класса CurrencyExchangeHandler.
     * Метод возвращает HashMap с валютами и их курсами.
    **/
     public HashMap<String, Double> getAllCurrency () {
        return data.read();
    }

    /** Метод calculateRate - рассчитывает курс обмена между двумя валютами
     * Параметры метода: initialCurrency начальная валюта, resultCurrency  конечная валюта
     * Метод возвращает переменную типа double - курс обмена между заданными валютами
     **/
    public double calculateRate (CurrencyTitle initialCurrency, CurrencyTitle resultCurrency){
        HashMap<CurrencyTitle, Double> exchangeRate = data.read();

        //TODO - предусмотреть случай, когда курс = 0

        if (initialCurrency.equals(defaultCurrency)) {
            return 1 / exchangeRate.get(resultCurrency);
        } else if (resultCurrency.equals(defaultCurrency)) {
            return exchangeRate.get(initialCurrency);
        } else {
            return exchangeRate.get(initialCurrency) / exchangeRate.get(resultCurrency);
        }
    }

    /** Метод calculateTotalAmount - рассчитывает конечную сумму после обмена.
     * Параметры метода: InitialCurrencyAmount - сумма обмена, initialCurrency начальная валюта, resultCurrency  конечная валюта
     * Метод возвращает переменную типа double - конечная сумма после обмена
    **/
    public double calculateTotalAmount (double InitialCurrencyAmount,CurrencyTitle initialCurrency, CurrencyTitle resultCurrency) {
        return calculateRate(initialCurrency, resultCurrency) * InitialCurrencyAmount;

}
}
