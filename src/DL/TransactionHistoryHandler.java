package DL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionHistoryHandler {
    private final String path;


    public TransactionHistoryHandler(String path) {
        create(path);
        this.path = path;
    }


    private boolean create(String path) {
        File file = new File(path);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<Transaction> read(){
        ArrayList<Transaction> result = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(this.path))){
            while (scanner.hasNextLine()) {
                String newTask = scanner.nextLine();
                String[] propertiesOfTransaction = newTask.split(";");
                Transaction transaction = new Transaction(
                        propertiesOfTransaction[0],
                        Double.parseDouble(propertiesOfTransaction[1].replace(",",".")),
                        CurrencyTitle.valueOf(propertiesOfTransaction[2]),
                        CurrencyTitle.valueOf(propertiesOfTransaction[3]),
                        Double.parseDouble(propertiesOfTransaction[4].replace(",",".")));
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
            fileWriter.append(convertingTransaction(transaction)+"\n");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private String convertingTransaction(Transaction transaction){
        return String.format("%s;%.1f;%s;%s;%.2f",
                transaction.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                transaction.getInitialCurrencyAmount(),
                transaction.getInitialCurrencyTitle(),
                transaction.getResultCurrencyTitle(),
                transaction.getExchangeRate());
    }
}
