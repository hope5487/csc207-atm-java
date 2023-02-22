package ATM.GUI.ForUser.Controller;

import ATM.Accounts.CreditCardsAccount;
import ATM.BankUsers.BankWorker;
import ATM.MainOperation.ATM;
import ATM.Accounts.Account;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class CurrencyExchangeMenuGUIController {

    @FXML
    private ChoiceBox<Integer> choiceAccount;

    @FXML
    private ChoiceBox<String> choiceCurrencyType;

    @FXML
    private TextField choiceExchangeAmount;

    @FXML
    private Label systemMessage;

    private final ArrayList<Account> accountList = ATM.dataStorage.getAccountStorage().getAccountList();

    public void pressExchangeConfirm() {
        try {
            int accountNumber = choiceAccount.getValue();
            String currencyType = choiceCurrencyType.getValue();
            double amount = Double.parseDouble(choiceExchangeAmount.getText());
            double returnAmount = currencyConversion(amount);
            for (Account account : accountList){
                if (account.getAccountNumber() == accountNumber){
                    account.transferOut(amount);
                    ATM.dataStorage.getTransactionStorage().writeOutgoingFileForExchanges(
                            ATM.currentUser, account, currencyType, amount, returnAmount); //append outgoing.txt
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
        systemMessage.setText("You need to choose account, currency, and amount!");
    }

    //Go back to main menu
    public void pressBack(){
        systemMessage.setText("");
        choiceAccount.getItems().clear();
        choiceExchangeAmount.clear();
        choiceCurrencyType.getItems().clear();
        if (ATM.currentUser instanceof BankWorker){
            ATM.window.setScene(ATM.scenes.bankWorkerMenuScene);
        } else {
            ATM.window.setScene(ATM.scenes.userMainMenuScene);
        }
    }

    //Make account choices when user click choice box.
    public void setChoiceAccount(){
        choiceAccount.getItems().clear();
        choiceExchangeAmount.clear();
        choiceCurrencyType.getItems().clear();
        for (Account account : accountList){
            if (account.getOwnerNameList().contains(ATM.currentUser.getUserName()) &&
                    !(account instanceof CreditCardsAccount)){
                choiceAccount.getItems().add(account.getAccountNumber());
            }
        }
    }

    public void setChoiceCurrencyType() {
        // want to add these options for a user to select: GBP, USD, JPY, CHF, and, AUS
        choiceCurrencyType.getItems().add("GBP");
        choiceCurrencyType.getItems().add("USD");
        choiceCurrencyType.getItems().add("JPY");
        choiceCurrencyType.getItems().add("CHF");
        choiceCurrencyType.getItems().add("AUS");
    }

    private double currencyConversion(double amount) {
        String currencyType = choiceCurrencyType.getValue();
        double conversion = 0;
        switch (currencyType) {
            case "GBP":
                conversion = 0.56 * amount;
                break;
            case "USD":
                conversion = 0.75 * amount;
                break;
            case "JPY":
                conversion = 82.20 * amount;
                break;
            case "CHF":
                conversion = 0.74 * amount;
                break;
            case "AUS":
                conversion = 1.05 * amount;
                break;
        }
        return conversion;
    }
}
