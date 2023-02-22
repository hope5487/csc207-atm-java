package ATM.DataStorage;

import ATM.Accounts.Account;
import ATM.BankUsers.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionStorage {

    private final ArrayList<Transaction> transactionList = new ArrayList<>();

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

    private final String transactionFilePath = System.getProperty("user.dir")
            + "/phase2/inputTextFiles/transaction.txt";

    private final String outgoingFilePath = System.getProperty("user.dir")
            + "/phase2/outputTextFiles/outgoing.txt";

    void readTransaction(){
        try {
            Scanner scanner = new Scanner(new File(transactionFilePath));
            while (scanner.hasNextLine()) {
                String[] record = scanner.nextLine().split(",");
                Transaction transaction = new Transaction(record[1], Integer.parseInt(record[2]),
                        Integer.parseInt(record[3]), Double.parseDouble(record[4]));
                transaction.setIsAbleToUndo(Boolean.parseBoolean(record[5]));
                transactionList.add(transaction);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeTransaction(){
        FileWriter fw;
        try {
            fw = new FileWriter(new File(transactionFilePath));
            for (Transaction transaction : transactionList){
                fw.write(transaction.getTransactionNumber() + "," + transaction.getType()
                        + "," + transaction.getAccountNumber() + "," + transaction.getAccountNumberOfOther()
                        + "," + transaction.getAmount() + "," + transaction.getIsAbleToUndo() + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeOutgoingFileForPayBill(User user, Account account, double amount, String recipient){
        FileWriter fw;
        try {
            fw = new FileWriter(new File(outgoingFilePath), true);
            fw.write("User " + user.getUserName()
                    + " transferred out $" + amount
                    + " from " + account.getAccountNumber()
                    + " to " + recipient);
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeOutgoingFileForExchanges(User user, Account account, String currency,
                                              double amount, double exchangeReturn){
        FileWriter fw;
        try {
            fw = new FileWriter(new File(outgoingFilePath), true);
            fw.write("User " + user.getUserName()
                    + " exchanged $" + amount
                    + " from " + account.getAccountNumber()
                    + " to " + currency
                    + " and was returned " + exchangeReturn);
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
