package ATM.GUI.ForUser.Controller;

import ATM.BankUsers.BankWorker;
import ATM.MainOperation.ATM;
import ATM.Accounts.Account;
import ATM.DataStorage.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class PayBillMenuGUIController {

    @FXML
    private TextField payBillAmountTextField, recipientTextField;

    @FXML
    private ChoiceBox<Integer> choiceAccountToSend;

    @FXML
    private Label systemMessage;

    private final ArrayList<Account> accountList = ATM.dataStorage.getAccountStorage().getAccountList();
    private final ArrayList<Transaction> transactionList = ATM.dataStorage.getTransactionStorage().getTransactionList();

    //Go back to main menu
    public void pressBack(){
        choiceAccountToSend.getItems().clear();
        payBillAmountTextField.clear();
        recipientTextField.clear();
        systemMessage.setText("");
        if (ATM.currentUser instanceof BankWorker){
            ATM.window.setScene(ATM.scenes.bankWorkerMenuScene);
        } else {
            ATM.window.setScene(ATM.scenes.userMainMenuScene);
        }
    }

    private void setSystemMessage(){
        systemMessage.setText("You need to choose account, amount, and recipient!");
    }

    public void pressConfirm(){
        try {
            double amount = Double.parseDouble(payBillAmountTextField.getText());
            Account account = accountList.get(choiceAccountToSend.getValue());
            if (account != null){
                account.pay(amount);
                ATM.dataStorage.getTransactionStorage().writeOutgoingFileForPayBill(ATM.currentUser, account, amount, recipientTextField.getText()); //append outgoing.txt
                transactionList.add(new Transaction("pay bill", choiceAccountToSend.getValue(), amount)); //record transaction
                ATM.dataStorage.getAccountStorage().writeAccountFile();
            }
            pressBack();
        } catch (Exception ex) {
            setSystemMessage();
        }
    }

    public void setChoiceAccountToSend(){
        choiceAccountToSend.getItems().clear();
        payBillAmountTextField.clear();
        for (Account account : accountList){
            if (account.getOwnerNameList().contains(ATM.currentUser.getUserName()) && account.getIsValid()){
                choiceAccountToSend.getItems().add(account.getAccountNumber());
            }
        }
    }
}
