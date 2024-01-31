import DL.Transaction;
import DL.TransactionHistoryHandler;
import UI.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        TransactionHistoryHandler data = new TransactionHistoryHandler("src/rates.txt");
        System.out.println(data.read());
        ConsoleUI.run();
    }
}
