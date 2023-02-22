package ATM.GUI.ForUser.Controller;

import ATM.BankUsers.BankWorker;
import ATM.MainOperation.ATM;
import ATM.Accounts.*;
import ATM.DataStorage.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class UserManageAccountGUIController {

    @FXML
    TextArea textArea;

    @FXML
    private ChoiceBox<Integer> choiceAccount;

    @FXML
    TextField newPassword;

    private final ArrayList<Account> accountList = ATM.dataStorage.getAccountStorage().getAccountList();
    private final ArrayList<Transaction> transactionList = ATM.dataStorage.getTransactionStorage().getTransactionList();

    //Go back to main menu
    public void pressBack(){
        textArea.clear();
        choiceAccount.getItems().clear();
        if (ATM.currentUser instanceof BankWorker){
            ATM.window.setScene(ATM.scenes.bankWorkerMenuScene);
        } else {
            ATM.window.setScene(ATM.scenes.userMainMenuScene);
        }
    }

    private void setErrorMessage(){
        textArea.setText("You need to choose account!");
    }

    public void setChoiceAccount(){
        choiceAccount.getItems().clear();
        for (Account account : accountList){
            if (account.getOwnerNameList().contains(ATM.currentUser.getUserName())){
                choiceAccount.getItems().add(account.getAccountNumber());
            }
        }
    }

    public void pressChangePassword() {
        String password = newPassword.getText();
        if (!password.equals("")){
            ATM.currentUser.setPassword(password);
            ATM.dataStorage.getUserStorage().writeUserFile();
            textArea.setText("Password changed!");
        } else {
            textArea.setText("Enter new password to change!");
        }
    }

    public void printAccountBalance() {
        try {
            Account account = accountList.get(choiceAccount.getValue());
            textArea.setText(String.valueOf(account.getBalance()));
        } catch (Exception ex) {
            setErrorMessage();
        }
    }

    public void printRecentTransactions() {
        try {
            textArea.clear();
            int accountNumber = choiceAccount.getValue();
            for (Transaction transaction : transactionList){
                if (transaction.getAccountNumber() == accountNumber ||
                        transaction.getAccountNumberOfOther() == accountNumber){
                    textArea.appendText(transaction + "\n");
                }
            }
        } catch (Exception ex) {
            setErrorMessage();
        }
    }

    public void printDateOfCreation() {
        try {
            Account account = accountList.get(choiceAccount.getValue());
            textArea.setText(account.getDateOfCreation().toString());
        } catch (Exception ex) {
            setErrorMessage();
        }
    }

    public void printNetTotal() {
        int assetBalance = 0;
        int debtBalance = 0;
        for (Account account : ATM.dataStorage.getAccountStorage().getAccountList()) {
            if (account.getOwnerNameList().contains(ATM.currentUser.getUserName())){
                if (account instanceof SavingAccount || account instanceof ChequingAccount) {
                    assetBalance += account.getBalance();
                } else {
                    debtBalance += account.getBalance();
                }
            }
        }
        textArea.setText("Net Total :$" + (assetBalance - debtBalance) + "\n");
        printSummary();
    }

    private void printSummary(){
        int chequeBalance = 0, savingBalance = 0, creditBalance = 0, lineOfCreditBalance = 0;
        for (Account account : ATM.dataStorage.getAccountStorage().getAccountList()) {
            if (account.getOwnerNameList().contains(ATM.currentUser.getUserName())){
                if (account instanceof ChequingAccount){
                    chequeBalance += account.getBalance();
                } else if (account instanceof SavingAccount){
                    savingBalance += account.getBalance();
                } else if (account instanceof CreditCardsAccount){
                    creditBalance += account.getBalance();
                } else if (account instanceof LineOfCreditAccount){
                    lineOfCreditBalance += account.getBalance();
                }
            }
        }
        textArea.appendText("Chequing Account Total :$" + chequeBalance + "\n" + "" +
                "Saving Account Total :$" + savingBalance + "\n" +
                "Credit Cards Account Total :$" + creditBalance + "\n" +
                "Line of Credit Account Total :$" + lineOfCreditBalance + "\n");
    }

    public void setPrimaryAccount() {
        try {
            if (accountList.get(choiceAccount.getValue()) instanceof ChequingAccount){
                ATM.currentUser.setPrimaryAccountNumber(choiceAccount.getValue());
                ATM.dataStorage.getUserStorage().writeUserFile();
                textArea.setText("Account No." +  choiceAccount.getValue()
                        + " has been set as primary account!");
            } else { textArea.setText("This is not chequing account!"); }
        } catch (Exception ex) {
            setErrorMessage();
        }
    }
}
