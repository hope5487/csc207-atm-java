package ATM.GUI.ForUser.Controller;

import ATM.BankUsers.BankWorker;
import ATM.MainOperation.ATM;
import ATM.Accounts.Account;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ATM.DataStorage.Transaction;

import java.util.ArrayList;

public class DepositMenuGUIController {

    @FXML
    private TextField depositAmountTextField;

    @FXML
    private ChoiceBox<Integer> choiceAccount;

    @FXML
    private Label systemMessage;

    private final ArrayList<Account> accountList = ATM.dataStorage.getAccountStorage().getAccountList();
    private final ArrayList<Transaction> transactionList = ATM.dataStorage.getTransactionStorage().getTransactionList();

    //When user press confirm button, do deposit
    public void pressDepositConfirm(){
        try {
            int amount = Integer.parseInt(depositAmountTextField.getText());
            int accountNumber = choiceAccount.getValue();
            for (Account account : accountList){
                if (account.getAccountNumber() == accountNumber){
                    account.deposit(amount);
                    transactionList.add(new Transaction("deposit", accountNumber, amount)); //record transaction
                    ATM.dataStorage.getAccountStorage().writeAccountFile();
                    pressBack();
                    break;
                }
            }
        } catch (Exception ex){
            setSystemMessage();
        }
    }

    private void setSystemMessage(){
        systemMessage.setText("You need to choose account and amount!");
    }

    //Go back to main menu
    public void pressBack(){
        systemMessage.setText("");
        depositAmountTextField.clear();
        choiceAccount.getItems().clear();
        if (ATM.currentUser instanceof BankWorker){
            ATM.window.setScene(ATM.scenes.bankWorkerMenuScene);
        } else {
            ATM.window.setScene(ATM.scenes.userMainMenuScene);
        }
    }

    //Make account choices when user click choice box.
    public void setChoiceAccount(){
        choiceAccount.getItems().clear();
        for (Account account : accountList){
            if (account.getOwnerNameList().contains(ATM.currentUser.getUserName()) && (account.getIsValid())){
                choiceAccount.getItems().add(account.getAccountNumber());
            }
        }
    }
}
