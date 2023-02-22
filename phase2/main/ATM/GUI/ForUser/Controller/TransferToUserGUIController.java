package ATM.GUI.ForUser.Controller;

import ATM.BankUsers.BankWorker;
import ATM.Exceptions.MaxTransferOutExceededException;
import ATM.MainOperation.ATM;
import ATM.Accounts.CreditCardsAccount;
import ATM.DataStorage.Transaction;
import ATM.Accounts.Account;
import ATM.BankUsers.User;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class TransferToUserGUIController {

    @FXML
    private TextField transferAmountTextField;

    @FXML
    private ChoiceBox<Integer> choiceAccountToSend;

    @FXML
    private ChoiceBox<String> choiceRecipient;

    @FXML
    private Label systemMessage;

    private final ArrayList<User> userList = ATM.dataStorage.getUserStorage().getUserList();
    private final ArrayList<Account> accountList = ATM.dataStorage.getAccountStorage().getAccountList();
    private final ArrayList<Transaction> transactionList = ATM.dataStorage.getTransactionStorage().getTransactionList();

    //Go back to main menu
    public void pressBack(){
        choiceAccountToSend.getItems().clear();
        choiceRecipient.getItems().clear();
        transferAmountTextField.clear();
        systemMessage.setText("");
        if (ATM.currentUser instanceof BankWorker){
            ATM.window.setScene(ATM.scenes.bankWorkerMenuScene);
        } else {
            ATM.window.setScene(ATM.scenes.userMainMenuScene);
        }
    }

    private void setSystemMessage(){
        systemMessage.setText("You need to choose account, amount, and user!");
    }

    public void pressConfirm(){
        try {
            double amount = Double.parseDouble(transferAmountTextField.getText());
            Account accountToSend = accountList.get(choiceAccountToSend.getValue());
            User recipient = searchRecipient(choiceRecipient.getValue());
            Account accountToReceive = searchPrimaryAccountOfUser(recipient);
            if (accountToReceive != null){
                accountToReceive.transferIn(amount);
                accountToSend.transferOut(amount);
                transactionList.add(new Transaction("transfer", choiceAccountToSend.getValue(),
                        accountToReceive.getAccountNumber(), amount)); //record transaction
                ATM.dataStorage.getAccountStorage().writeAccountFile(); //save changed account/transaction
                pressBack();
            }
        } catch (MaxTransferOutExceededException max) {
            systemMessage.setText("You don't have enough balance!");
        } catch (Exception ex){
            setSystemMessage();
        }
    }

    //helper method to search user based on his name.
    private User searchRecipient(String recipientName){
        for (User user : userList){
            if (user.getUserName().equals(recipientName)){
                return user;
            }
        }
        return null;
    }

    //helper method to search primary account of user.
    private Account searchPrimaryAccountOfUser(User recipient){
        if (recipient != null){
            int accountNumber = recipient.getPrimaryAccountNumber();
            return ATM.dataStorage.getAccountStorage().getAccountList().get(accountNumber);
        }
        return null;
    }

    public void setChoiceAccountToSend(){
        choiceAccountToSend.getItems().clear();
        choiceRecipient.getItems().clear();
        for (Account account : accountList){
            if (account.getOwnerNameList().contains(ATM.currentUser.getUserName()) && !(account instanceof CreditCardsAccount) && (account.getIsValid())){
                choiceAccountToSend.getItems().add(account.getAccountNumber());
            }
        }
    }

    public void setChoiceRecipient(){
        choiceRecipient.getItems().clear();
        for (User user : userList){
            if (user != ATM.currentUser){
                choiceRecipient.getItems().add(user.getUserName());
            }
        }
    }
}
