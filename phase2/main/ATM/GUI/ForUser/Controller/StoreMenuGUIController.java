package ATM.GUI.ForUser.Controller;

import ATM.Accounts.Account;
import ATM.BankUsers.BankWorker;
import ATM.DataStorage.Transaction;
import ATM.Exceptions.NoItemToRemoveFromCartException;
import ATM.MainOperation.ATM;
import ATM.ShoppingPackage.Item;
import ATM.ShoppingPackage.ShoppingCart;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class StoreMenuGUIController {

    @FXML
    private ComboBox<String> itemComboBox;

    @FXML
    private ComboBox<Integer> amountComboBox;

    @FXML
    private Label systemMessage;

    @FXML
    private TextArea cartArea;

    private final ShoppingCart shoppingCart = new ShoppingCart();
    private final ArrayList<Account> accountList = ATM.dataStorage.getAccountStorage().getAccountList();
    private final ArrayList<Transaction> transactionList = ATM.dataStorage.getTransactionStorage().getTransactionList();

    //Go back to main menu
    public void pressBack(){
        itemComboBox.getItems().clear();
        amountComboBox.getItems().clear();
        systemMessage.setText("");
        cartArea.clear();
        if (ATM.currentUser instanceof BankWorker){
            ATM.window.setScene(ATM.scenes.bankWorkerMenuScene);
        } else {
            ATM.window.setScene(ATM.scenes.userMainMenuScene);
        }
    }

    public void pressAddToShoppingCart(){
        try {
            Item item = ATM.dataStorage.getItemStorage().searchItem(itemComboBox.getValue());
            shoppingCart.addItem(item, amountComboBox.getValue());
            updateCartArea();
        } catch (Exception ex){
            systemMessage.setText("You need to choose item and amount!");
        }
    }

    public void pressRemove(){
        try {
            Item item = ATM.dataStorage.getItemStorage().searchItem(itemComboBox.getValue());
            shoppingCart.removeItem(item, amountComboBox.getValue());
            updateCartArea();
        } catch (NullPointerException nullException){
            systemMessage.setText("You need to choose item and amount!");
        } catch (NoItemToRemoveFromCartException noItemException){
            systemMessage.setText("There is no such item in your cart!");
        }
    }

    public void pressCheckout(){
        try {
            Account account = accountList.get(ATM.currentUser.getPrimaryAccountNumber());
            if (account.getIsValid()){
                account.transferOut(shoppingCart.calculateTotal());
                ATM.dataStorage.getTransactionStorage().writeOutgoingFileForPayBill(ATM.currentUser, account, shoppingCart.calculateTotal(), "Shopping"); //append outgoing.txt
                transactionList.add(new Transaction("pay bill", account.getAccountNumber(), shoppingCart.calculateTotal())); //record transaction
                ATM.dataStorage.getAccountStorage().writeAccountFile();
                pressBack();
            } else {
                systemMessage.setText("Your primary account is not valid to use!");
            }
        } catch (Exception ex){
            systemMessage.setText("You don't have enough balance in your Chequing Account!");
        }
    }

    private void updateCartArea(){
        Account account = accountList.get(ATM.currentUser.getPrimaryAccountNumber());
        cartArea.setText("Current account balance : " + account.getBalance() + "\n");
        for (Item item : shoppingCart.getItems().keySet()){
            cartArea.appendText(item.getItemName() + " : " + shoppingCart.getItems().get(item) + "\n");
        }
        cartArea.appendText("Total price : $" + shoppingCart.calculateTotal());
    }

    public void setItemComboBox() {
        if (itemComboBox.getItems().isEmpty()){
            for (Item item : ATM.dataStorage.getItemStorage().getItemList()){
                itemComboBox.getItems().add(item.getItemName());
            }
        }
    }

    public void setAmountComboBox() {
        if (amountComboBox.getItems().isEmpty()){
            for (int i = 1; i < 11; i++){
                amountComboBox.getItems().add(i);
            }
        }
    }
}
