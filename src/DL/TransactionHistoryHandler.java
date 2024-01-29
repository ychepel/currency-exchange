package DL;

import java.io.File;
import java.io.IOException;

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
}
