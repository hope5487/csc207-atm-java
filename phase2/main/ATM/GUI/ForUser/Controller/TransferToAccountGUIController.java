package ATM.GUI.ForUser.Controller;

import ATM.BankUsers.BankWorker;
import ATM.MainOperation.ATM;
import ATM.Accounts.Account;
import ATM.Accounts.CreditCardsAccount;
import ATM.DataStorage.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class TransferToAccountGUIController {

    @FXML
    private TextField transferAmountTextField;

    @FXML
    private ChoiceBox<Integer> choiceAccountToSend, choiceAccountToReceive;

    @FXML
    private Label systemMessage;

    private final ArrayList<Account> accountList = ATM.dataStorage.getAccountStorage().getAccountList();
    private final ArrayList<Transaction> transactionList = ATM.dataStorage.getTransactionStorage().getTransactionList();

    //Go back to main menu
    public void pressBack(){
        choiceAccountToSend.getItems().clear();
        choiceAccountToReceive.getItems().clear();
        transferAmountTextField.clear();
        systemMessage.setText("");
        if (ATM.currentUser instanceof BankWorker){
            ATM.window.setScene(ATM.scenes.bankWorkerMenuScene);
        } else {
            ATM.window.setScene(ATM.scenes.userMainMenuScene);
        }
    }

    private void setSystemMessage(){
        systemMessage.setText("You need to choose account, amount, and bill type!");
    }

    public void pressConfirm(){
        try {
            double amount = Double.parseDouble(transferAmountTextField.getText());
            Account accountToSend = accountList.get(choiceAccountToSend.getValue());
            Account accountToReceive = accountList.get(choiceAccountToReceive.getValue());
            accountToSend.transferOut(amount);
            accountToReceive.transferIn(amount);
            transactionList.add(new Transaction("transfer", choiceAccountToSend.getValue(),
                    choiceAccountToReceive.getValue(), amount)); //record transaction
            ATM.dataStorage.getAccountStorage().writeAccountFile();
            pressBack();
        } catch (Exception ex) {
            setSystemMessage();
        }
    }

    //Make account choices when user click choice box.
    public void setChoiceAccountToSend(){
        choiceAccountToSend.getItems().clear();
        choiceAccountToReceive.getItems().clear();
        for (Account account : accountList){
            if (account.getOwnerNameList().contains(ATM.currentUser.getUserName())
                    && !(account instanceof CreditCardsAccount) && account.getIsValid()){
                choiceAccountToSend.getItems().add(account.getAccountNumber());
            }
        }
    }

    //Make account choices when user click choice box.
    public void setChoiceAccountToReceive(){
        choiceAccountToReceive.getItems().clear();
        if (choiceAccountToSend.getValue() != null){
            int accountToSendNumber = choiceAccountToSend.getValue();
            for (Account account : accountList){
                if (account.getOwnerNameList().contains(ATM.currentUser.getUserName()) &&
                        account.getAccountNumber() != accountToSendNumber && account.getIsValid()){
                    choiceAccountToReceive.getItems().add(account.getAccountNumber());
                }
            }
        }
    }
}
