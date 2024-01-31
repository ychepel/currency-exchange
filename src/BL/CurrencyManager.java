package BL;

import DL.CurrencyExchangeHandler;
import DL.Currency;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    public HashMap<Currency, Double> getAllCurrency() {
        return data.read();
    }

    /**
     * Метод calculateRate - рассчитывает курс обмена между двумя валютами
     * Параметры метода: initialCurrency начальная валюта, resultCurrency конечная валюта
     * Метод возвращает переменную типа double - курс обмена между заданными валютами
     **/
    public double calculateRate(Currency initialCurrency, Currency resultCurrency) {
        HashMap<Currency, Double> exchangeRates = data.read();
        return exchangeRates.get(resultCurrency) / exchangeRates.get(initialCurrency);
    }

    /**
     * Метод calculateTotalAmount - рассчитывает конечную сумму после обмена.
     * Параметры метода: InitialCurrencyAmount - сумма обмена, initialCurrency начальная валюта, resultCurrency конечная валюта
     * Метод возвращает переменную типа double - конечная сумма после обмена
     **/
    public double calculateTotalAmount(double InitialCurrencyAmount, Currency initialCurrency, Currency resultCurrency) {
        return calculateRate(initialCurrency, resultCurrency) * InitialCurrencyAmount;
    }

    /**
     * Метод getAvailableCurrencies - сравнивает значения из enum Currency
     * и значения из файла с курсами валют
     * Метод возвращает массив из доступных значений Currency
     **/
    public List<Currency> getAvailableCurrencies() {
        HashMap<Currency, Double> currencyFile = data.read();
        Currency[] currencies= currencyFile.keySet().toArray(new Currency[0]);

        List<Currency> listFile = Arrays.asList(currencies);

        return listFile;

    }


}

