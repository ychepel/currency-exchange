package DL;

import UI.ApplicationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class TransactionHistoryHandler {

    private File file;


    public TransactionHistoryHandler(String path) {
        this.file = new File(path);
        createIfNotExists(path);
    }


    private void createIfNotExists(String path) {
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            ErrorLogHandler.add(e);
            throw new ApplicationException();
        }
    }

    public ArrayList<Transaction> read() {
        ArrayList<Transaction> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(this.file)) {
            while (scanner.hasNextLine()) {
                String newTransaction = scanner.nextLine();
                String[] properties = newTransaction.split(";");
                try {
                    LocalDateTime dateTime = convertToDateTime(properties[0]);
                    double initialAmount = Double.parseDouble(properties[1].replace(",", "."));
                    Currency initialCurrency = Currency.valueOf(properties[2]);
                    Currency resultCurrency = Currency.valueOf(properties[3]);
                    double rate = Double.parseDouble(properties[4].replace(",", "."));
                    Transaction transaction = new Transaction(dateTime, initialAmount, initialCurrency, resultCurrency, rate);
                    result.add(transaction);
                } catch (RuntimeException exception) {
                    ErrorLogHandler.add(exception);
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            ErrorLogHandler.add(e);
            throw new ApplicationException();
        }
    }


    public void append(Transaction transaction) {
        try (FileWriter fileWriter = new FileWriter(this.file, true)) {
            if (isEmpty()) {
                fileWriter.append(getFormatted(transaction));
            } else {
                fileWriter.append("\n" + getFormatted(transaction));
            }
        } catch (IOException e) {
            ErrorLogHandler.add(e);
            throw new ApplicationException();
        }
    }

    private boolean isEmpty() {
        //TODO: method should be optimised for not reading all data each time -> this.file.length()
        return read().isEmpty();
    }

    private String getFormatted(Transaction transaction) {
        return String.format("%s;%.1f;%s;%s;%.2f",
                transaction.getDateTime(),
                transaction.getInitialCurrencyAmount(),
                transaction.getInitialCurrencyTitle(),
                transaction.getResultCurrencyTitle(),
                transaction.getExchangeRate());
    }

    private LocalDateTime convertToDateTime(String datetime) {
        //TODO: we should format data before storing in file. there may be different quantity of ms (real examples: 2024-01-30T20:55:42.391690100, 2024-01-30T23:05:37.400644)
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
        return LocalDateTime.parse(datetime, dateTimeFormatter);
    }
}
