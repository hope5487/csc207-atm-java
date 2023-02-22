package ATM.BankUsers;

import ATM.Accounts.*;
import ATM.Exceptions.CannotWithdrawFromCreditCardException;
import ATM.Exceptions.MaxTransferOutExceededException;
import ATM.Exceptions.UnableToUndoTransactionException;
import ATM.MainOperation.ATM;
import ATM.DataStorage.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/* The bank manager should be able to create a login and set the initial password for a user.
They can increase the number of $5, $10, $20, and/or $50 bills in the machine to simulate restocking the machine.
Has the ability to undo the most recent transaction on any asset or debt account, except for paying bills.*/
public class BankManager extends BankWorker{

    private final Map<Integer, Integer> moneyInATM = ATM.dataStorage.getBillStorage().getNumberOfBills();
    private final ArrayList<Account> accountList = ATM.dataStorage.getAccountStorage().getAccountList();

    public void promoteToBankWorker(User user){
        BankWorker bankWorker = new BankWorker(user.getUserName(), user.getPassword(),
                user.getPrimaryAccountNumber(), user.getPortfolioNumber());
        ArrayList<User> userList = ATM.dataStorage.getUserStorage().getUserList();
        userList.remove(user);
        userList.add(bankWorker);
        ATM.dataStorage.getUserStorage().writeUserFile();
    }

    public void demoteToUser(BankWorker bankWorker){
        User user = new User(bankWorker.getUserName(), bankWorker.getPassword(),
                bankWorker.getPrimaryAccountNumber(), bankWorker.getPortfolioNumber());
        ArrayList<User> userList = ATM.dataStorage.getUserStorage().getUserList();
        userList.remove(bankWorker);
        userList.add(user);
        ATM.dataStorage.getUserStorage().writeUserFile();
    }

    public void undoTransaction(int transactionNumber) throws MaxTransferOutExceededException,
            CannotWithdrawFromCreditCardException, UnableToUndoTransactionException {
        Transaction transaction = ATM.dataStorage.getTransactionStorage().getTransactionList().get(transactionNumber);
        if (!transaction.getIsAbleToUndo()){ throw new UnableToUndoTransactionException(); }
        Account account = searchAccount1(transaction);
        Account otherAccount = searchAccount2(transaction);
        helperUndoTransaction(account, otherAccount, transaction);
        transaction.setIsAbleToUndo(false);
        ATM.dataStorage.getAccountStorage().writeAccountFile();
    }

    private void helperUndoTransaction(Account account, Account otherAccount, Transaction transaction) throws
            CannotWithdrawFromCreditCardException, MaxTransferOutExceededException{
        if (account != null && otherAccount != null){
            account.transferIn(transaction.getAmount());
            otherAccount.transferOut(transaction.getAmount());
        } else if (account != null){
            switch (transaction.getType()) {
                case "deposit":
                    account.transferOut(transaction.getAmount());
                    break;
                case "withdraw":
                    account.transferIn(transaction.getAmount());
                    break;
            }
        }
    }

    private Account searchAccount1(Transaction transaction){
        for (Account accountToSearch : accountList){
            if (transaction.getAccountNumber() == accountToSearch.getAccountNumber()){
                return accountToSearch;
            }
        }
        return null;
    }

    private Account searchAccount2(Transaction transaction){
        if (transaction.getAccountNumberOfOther() != -1){
            for (Account accountToSearch : accountList){
                if (transaction.getAccountNumberOfOther() == accountToSearch.getAccountNumber()){
                    return accountToSearch;
                }
            }
        }
        return null;
    }

    //methods to restock bills in ATM machine.
    public void restockBill(String billType, int amount){
        BillRestock billRestock = new BillRestock();
        billRestock.restockBill(billType, amount);
    }

    //check bills in ATM machine. This methods need to be called when user transferOut any money.
    public void checkBillsInATM(){
        for (Integer key : moneyInATM.keySet()) {
            if (moneyInATM.get(key) < 20){
                sendAlert();
                break;
            }
        }
    }

    //helper method for checkBillsInATm() to send alert to the bank manager.
    private void sendAlert(){
        FileWriter fw;
        try {
            fw = new FileWriter(new File(System.getProperty("user.dir")
                    + "/phase2/outputTextFiles/alert.txt"));
            fw.write("ATM currently has " + moneyInATM.get(5) + " 5 dollars, "
                    + moneyInATM.get(10) + " 10 dollars, "
                    + moneyInATM.get(20) + " 20 dollars, "
                    + moneyInATM.get(50) + " 50 dollars.\n Add money!");
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
// is the restocking affected by the accounts
// does bank manager keep track of how much money is in the atm
}
