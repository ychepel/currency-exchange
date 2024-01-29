package DL;

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
        createIfNotExists(path);
    }


    private void createIfNotExists(String path) {
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Transaction> read(){
        ArrayList<Transaction> result = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(this.path))){
            while (scanner.hasNextLine()) {
                String newTransaction = scanner.nextLine();
                String[] properties = newTransaction.split(";");
                Transaction transaction = new Transaction(
                        convertingStringToDateTime(properties[0]),
                        Double.parseDouble(properties[1].replace(",",".")),
                        CurrencyTitle.valueOf(properties[2]),
                        CurrencyTitle.valueOf(properties[3]),
                        Double.parseDouble(properties[4].replace(",",".")));
                result.add(transaction);
            }
            return result;
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return result;
    }


    public void append(Transaction transaction){
        try (FileWriter fileWriter = new FileWriter(this.path,true)) {
            fileWriter.append(convertedTransaction(transaction)+"\n");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private String convertedTransaction(Transaction transaction){
        return String.format("%s;%.1f;%s;%s;%.2f",
                transaction.getDateTime(),
                transaction.getInitialCurrencyAmount(),
                transaction.getInitialCurrencyTitle(),
                transaction.getResultCurrencyTitle(),
                transaction.getExchangeRate());
    }

    private LocalDateTime convertingStringToDateTime(String datetime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
        return LocalDateTime.parse(datetime, dateTimeFormatter);
    }
}
