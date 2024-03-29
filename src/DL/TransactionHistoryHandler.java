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
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public TransactionHistoryHandler(String path) {
        this.file = new File(path);
        createIfNotExists();
    }


    private void createIfNotExists() {
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
        return this.file.length() == 0;
    }

    private String getFormatted(Transaction transaction) {
        return String.format("%s;%.1f;%s;%s;%.2f",
                transaction.getDateTime().format(dateFormat),
                transaction.getInitialCurrencyAmount(),
                transaction.getInitialCurrencyTitle(),
                transaction.getResultCurrencyTitle(),
                transaction.getExchangeRate());
    }

    private LocalDateTime convertToDateTime(String datetime) {
        return LocalDateTime.parse(datetime, dateFormat);
    }
}
