package DL;

import UI.ApplicationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class CurrencyExchangeHandler {
    private File file;

    public CurrencyExchangeHandler(String path) {
        this.file = new File(path);
    }

    public HashMap<Currency, Double> read() {
        HashMap<Currency, Double> hashMapList = new HashMap<>();
        Scanner scanner;
        try {
            scanner = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            ErrorLogHandler.add(e);
            throw new ApplicationException();
        }

        while (scanner.hasNextLine()) {
            String currencyRate = scanner.nextLine();
            String[] preHashMap = currencyRate.split(";");
            try {
                Currency currency = Currency.valueOf(preHashMap[0]);
                double rate = Double.parseDouble(preHashMap[1].replace(",", "."));
                if (rate == 0) throw new IllegalArgumentException("There is zero currency rate for " + currencyRate + "in data source.");
                hashMapList.put(currency, rate);
            } catch (RuntimeException exception) {
                ErrorLogHandler.add(exception);
            }
        }
        return hashMapList;
    }
}
