package ATM.GUI.ForUser.Controller;

import ATM.BankUsers.BankWorker;
import ATM.MainOperation.ATM;
import ATM.Accounts.Account;
import ATM.Accounts.CreditCardsAccount;
import ATM.DataStorage.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Map;

public class WithdrawMenuGUIController {

    @FXML
    private ChoiceBox<Integer> choiceAccount, choiceWithdrawAmount, choiceBillType;

    @FXML
    private Label systemMessage;

    private final ArrayList<Account> accountList = ATM.dataStorage.getAccountStorage().getAccountList();
    private final ArrayList<Transaction> transactionList = ATM.dataStorage.getTransactionStorage().getTransactionList();

    public void pressWithdrawConfirm() {
        try {
            int amount = choiceWithdrawAmount.getValue();
            int billType = choiceBillType.getValue();
            int accountNumber = choiceAccount.getValue();
            for (Account account : accountList){
                if (account.getAccountNumber() == accountNumber){
                    account.withdraw(billType, amount);
                    transactionList.add(new Transaction("withdraw", accountNumber, amount)); //record transaction
                    ATM.dataStorage.getAccountStorage().writeAccountFile();
                    pressBack();
                    break;
                }
            }
        } catch (Exception ex) {
            setSystemMessage();
        }
    }

    private void setSystemMessage(){
        systemMessage.setText("You need to choose account, amount, and bill type!");
    }

    //Go back to main menu
    public void pressBack(){
        systemMessage.setText("");
        choiceAccount.getItems().clear();
        choiceWithdrawAmount.getItems().clear();
        choiceBillType.getItems().clear();
        if (ATM.currentUser instanceof BankWorker){
            ATM.window.setScene(ATM.scenes.bankWorkerMenuScene);
        } else {
            ATM.window.setScene(ATM.scenes.userMainMenuScene);
        }
    }

    //Make account choices when user click choice box.
    public void setChoiceAccount(){
        choiceAccount.getItems().clear();
        choiceWithdrawAmount.getItems().clear();
        choiceBillType.getItems().clear();
        for (Account account : accountList){
            if (account.getOwnerNameList().contains(ATM.currentUser.getUserName()) &&
                    !(account instanceof CreditCardsAccount) && (account.getIsValid())){
                choiceAccount.getItems().add(account.getAccountNumber());
            }
        }
    }

    public void setChoiceWithdrawAmount(){
        choiceWithdrawAmount.getItems().clear();
        choiceBillType.getItems().clear();
        if (choiceAccount.getValue() != null){
            double balance = accountList.get(choiceAccount.getValue()).getBalance();
            if (balance >= 1000){ choiceWithdrawAmount.getItems().add(1000); }
            if (balance >= 500) { choiceWithdrawAmount.getItems().add(500); }
            if (balance >= 200) { choiceWithdrawAmount.getItems().add(200); }
            if (balance >= 100) { choiceWithdrawAmount.getItems().add(100); }
            if (balance >= 50) { choiceWithdrawAmount.getItems().add(50); }
            if (balance >= 20) { choiceWithdrawAmount.getItems().add(20); }
        }
    }

    public void setChoiceBillType(){
        choiceBillType.getItems().clear();
        Integer amount = choiceWithdrawAmount.getValue();
        if (amount != null){
            helperChoiceBillType(amount);
        }
    }

    private void helperChoiceBillType(int amount){
        Map<Integer, Integer> bills = ATM.dataStorage.getBillStorage().getNumberOfBills();
        if (amount >= 50 && bills.get(50) >= amount / 50) {
            choiceBillType.getItems().add(50);
        }
        if (amount >= 20 && bills.get(20) >= amount / 20) {
            choiceBillType.getItems().add(20);
        }
        if (bills.get(10) >= amount / 10) { choiceBillType.getItems().add(10); }
        if (bills.get(5) >= amount / 5) { choiceBillType.getItems().add(5); }
    }
}
