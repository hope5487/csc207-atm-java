package ATM.DataStorage;

import ATM.MainOperation.ATM;
import ATM.Accounts.*;
import ATM.BankUsers.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountStorage {

    private final ArrayList<Account> accountList = new ArrayList<>();
    public ArrayList<Account> getAccountList() {
        return accountList;
    }

    private final String accountFilePath = System.getProperty("user.dir")
            + "/phase2/inputTextFiles/account.txt";

    public void readAccountFile(){
        try {
            Scanner scanner = new Scanner(new File(accountFilePath));
            while (scanner.hasNextLine()) {
                String[] record = scanner.nextLine().split(",");
                getAccountList().add(selectAccountType(record));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ATM.dataStorage.getTransactionStorage().readTransaction();
    }

    public void writeAccountFile(){
        FileWriter fw;
        try {
            fw = new FileWriter(new File(accountFilePath));
            for (Account account : accountList){
                String ownerNames = buildListOfUserNames(account).toString(); //owner names
                checkAccountTypeAndWrite(fw, account);
                fw.write(account.getAccountNumber() + "," + account.getDateOfCreation()
                        + "," + account.getValidDate() + "," + account.getBalance()
                        + "," + ownerNames + "," + account.getIsValid() + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ATM.dataStorage.getTransactionStorage().writeTransaction();
    }

    //belows are helper methods for read/write txt.file.
    private Account selectAccountType(String[] record){
        switch (record[0]) {
            case "ChequingAccount": {
                return addChequeAccount(record);
            }
            case "SavingAccount": {
                return addSavingAccount(record);
            }
            case "CreditCardsAccount": {
                return addCreditCardAccount(record);
            }
            case "LineOfCreditAccount": {
                return addLineOfCreditAccount(record);
            }
            case "StudentAccount": {
                return addStudentAccount(record);
            }
        }
        return null;
    }

    private StringBuilder buildListOfUserNames(Account account){
        StringBuilder ownerNames = new StringBuilder();
        for (int i = 0; i < account.getOwnerNameList().size(); i++){
            ownerNames.append(account.getOwnerNameList().get(i));
            if (i < account.getOwnerNameList().size() - 1){
                ownerNames.append("/");
            }
        }
        return ownerNames;
    }

    private void checkAccountTypeAndWrite(FileWriter fw, Account account) throws IOException{
        if (account instanceof StudentAccount){
            fw.write("StudentAccount,");
        } else if (account instanceof ChequingAccount){
            fw.write("ChequingAccount,");
        } else if (account instanceof SavingAccount){
            fw.write("SavingAccount,");
        } else if (account instanceof CreditCardsAccount){
            fw.write("CreditCardsAccount,");
        } else if (account instanceof LineOfCreditAccount){
            fw.write("LineOfCreditAccount,");
        }
    }

    private ChequingAccount addChequeAccount(String[] record){
        ChequingAccount account = new ChequingAccount();
        accountSetter(account, record);
        return account;
    }

    private StudentAccount addStudentAccount(String[] record){
        StudentAccount account = new StudentAccount();
        accountSetter(account, record);
        return account;
    }

    private SavingAccount addSavingAccount(String[] record){
        SavingAccount account = new SavingAccount();
        accountSetter(account, record);
        return account;
    }

    private CreditCardsAccount addCreditCardAccount(String[] record){
        CreditCardsAccount account = new CreditCardsAccount();
        accountSetter(account, record);
        return account;
    }

    private LineOfCreditAccount addLineOfCreditAccount(String[] record){
        LineOfCreditAccount account = new LineOfCreditAccount();
        accountSetter(account, record);
        return account;
    }

    private void accountSetter(Account account, String[] record){
        setDateOfCreation(account, record);
        setValidDate(account, record);
        account.setBalance(Double.parseDouble(record[4]));
        for (String userName : record[5].split("/")){
            for (User user : ATM.dataStorage.getUserStorage().getUserList()){
                if (user.getUserName().equals(userName)){
                    account.addOwner(userName);
                    break;
                }
            }
        }
        boolean isValid = Boolean.parseBoolean(record[6]);
        account.setIsValid(isValid);
    }

    private void setDateOfCreation(Account account, String[] record){
        String[] dateOfCreation = record[2].split("/");
        int yearFrom = Integer.parseInt(dateOfCreation[0]);
        int monthFrom = Integer.parseInt(dateOfCreation[1]);
        int dayFrom = Integer.parseInt(dateOfCreation[2]);
        account.setDateOfCreation(yearFrom, monthFrom, dayFrom);
    }

    private void setValidDate(Account account, String[] record){
        String[] dateOfValid = record[3].split("/");
        int yearTo = Integer.parseInt(dateOfValid[0]);
        int monthTo = Integer.parseInt(dateOfValid[1]);
        int dayTo = Integer.parseInt(dateOfValid[2]);
        account.setValidDate(yearTo, monthTo, dayTo);
    }
}
