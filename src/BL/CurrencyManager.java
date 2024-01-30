package BL;

import DL.CurrencyExchangeHandler;
import DL.CurrencyTitle;

import java.util.ArrayList;
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
    public HashMap<CurrencyTitle, Double> getAllCurrency() {
        return data.read();
    }

    /**
     * Метод calculateRate - рассчитывает курс обмена между двумя валютами
     * Параметры метода: initialCurrency начальная валюта, resultCurrency конечная валюта
     * Метод возвращает переменную типа double - курс обмена между заданными валютами
     **/
    public double calculateRate(CurrencyTitle initialCurrency, CurrencyTitle resultCurrency) {
        HashMap<CurrencyTitle, Double> exchangeRates = data.read();
        return exchangeRates.get(resultCurrency) / exchangeRates.get(initialCurrency);
    }

    /**
     * Метод calculateTotalAmount - рассчитывает конечную сумму после обмена.
     * Параметры метода: InitialCurrencyAmount - сумма обмена, initialCurrency начальная валюта, resultCurrency конечная валюта
     * Метод возвращает переменную типа double - конечная сумма после обмена
     **/
    public double calculateTotalAmount(double InitialCurrencyAmount, CurrencyTitle initialCurrency, CurrencyTitle resultCurrency) {
        return calculateRate(initialCurrency, resultCurrency) * InitialCurrencyAmount;
    }

    /**
     * Метод getCurrencyTitles - сравнивает значения из enum CurrencyTitle
     * и значения из файла с курсами валют
     * Метод возвращает массив из CurrencyTitle, в котором содержаться только общие
     * элементы
     **/
    public List<CurrencyTitle> getAvailableCurrencies() {
        HashMap<CurrencyTitle, Double> currencyFile = data.read();
        CurrencyTitle[] currencyTitlesFile = currencyFile.keySet().toArray(new CurrencyTitle[0]);

        List<CurrencyTitle> listFile = Arrays.asList(currencyTitlesFile);

        return listFile;

    }


}

