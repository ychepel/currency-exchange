package DL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class CurrencyExchangeHandler {
    private File file;

    public CurrencyExchangeHandler(String path){
        this.file = new File(path);
    }

    public HashMap<CurrencyTitle, Double> read(){
        HashMap<CurrencyTitle, Double> hashMapList = new HashMap<>();
        try (Scanner scanner = new Scanner(this.file)){
            while (scanner.hasNextLine()){
                String currencyRate = scanner.nextLine();
                String[] preHashMap = currencyRate.split(",");
                hashMapList.put(CurrencyTitle.valueOf(preHashMap[0]),Double.parseDouble(preHashMap[1]));
            }
        }catch (FileNotFoundException e){
            System.out.println("Error: "+e.getMessage());
        }
        return hashMapList;
    }
}
