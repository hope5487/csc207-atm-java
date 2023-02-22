package ATM.GUI.ForManager.Controller;

import ATM.Accounts.Account;
import ATM.BankUsers.User;
import ATM.DataStorage.Transaction;
import ATM.MainOperation.ATM;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ManagerManageAccountGUIController {

    @FXML
    TextArea textArea;

    @FXML
    TextField userNameTextField;

    @FXML
    private ChoiceBox<Integer> choiceAccount;

    @FXML
    private ChoiceBox<String> choiceUser;

    private final ArrayList<User> userList = ATM.dataStorage.getUserStorage().getUserList();
    private final ArrayList<Account> accountList = ATM.dataStorage.getAccountStorage().getAccountList();
    private final ArrayList<Transaction> transactionList = ATM.dataStorage.getTransactionStorage().getTransactionList();

    //Go back to main menu
    public void pressBack(){
        textArea.clear();
        choiceAccount.getItems().clear();
        ATM.window.setScene(ATM.scenes.managerMainMenuScene);
    }

    private void setErrorMessage(){
        textArea.setText("You need to choose user or account or both!");
    }

    public void setChoiceUser(){
        choiceUser.getItems().clear();
        for (User user : userList){
            choiceUser.getItems().add(user.getUserName());
        }
    }

    public void setChoiceAccount(){
        try {
            choiceAccount.getItems().clear();
            for (Account account : accountList){
                choiceAccount.getItems().add(account.getAccountNumber());
            }
        } catch (Exception ex) {
            setErrorMessage();
        }
    }

    public void addAccountUser() {
        try {
            Account account = accountList.get(choiceAccount.getValue());
            account.addOwner(choiceUser.getValue());
            ATM.dataStorage.getAccountStorage().writeAccountFile();
            textArea.setText("Added account user!");
        } catch (Exception ex) {
            setErrorMessage();
        }
    }

    public void removeAccountUser() {
        try {
            Account account = accountList.get(choiceAccount.getValue());
            account.removeOwner(choiceUser.getValue());
            ATM.dataStorage.getAccountStorage().writeAccountFile();
            textArea.setText("Removed account user!");
        } catch (Exception ex) {
            setErrorMessage();
        }
    }

    public void renewAccount() {
        try {
            Account account = accountList.get(choiceAccount.getValue());
            account.renewValidDate();
            ATM.dataStorage.getAccountStorage().writeAccountFile();
            textArea.setText("Account renewed!");
        } catch (Exception ex) {
            setErrorMessage();
        }
    }

    public void activateAccount() {
        try {
            Account account = accountList.get(choiceAccount.getValue());
            account.setIsValid(true);
            ATM.dataStorage.getAccountStorage().writeAccountFile();
            textArea.setText("Account activated!");
        } catch (Exception ex) {
            setErrorMessage();
        }
    }

    public void deactivateAccount() {
        try {
            Account account = accountList.get(choiceAccount.getValue());
            account.setIsValid(false);
            ATM.dataStorage.getAccountStorage().writeAccountFile();
            textArea.setText("Account deactivated!");
        } catch (Exception ex) {
            setErrorMessage();
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

    public void resetPassword() {
        try {
            User user = ATM.dataStorage.getUserStorage().searchUser(choiceUser.getValue());
            user.setPassword("0000");
            textArea.setText("Password reset!");
        } catch (Exception ex) {
            textArea.setText("You need to choose user!");
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
}
