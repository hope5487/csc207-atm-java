package ATM.GUI.ForManager.Controller;

import ATM.DataStorage.Transaction;
import ATM.Exceptions.CannotWithdrawFromCreditCardException;
import ATM.Exceptions.MaxTransferOutExceededException;
import ATM.Exceptions.UnableToUndoTransactionException;
import ATM.MainOperation.ATM;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class ManagerUndoTransactionGUIController {

    @FXML
    ComboBox<Integer> choiceTransactionNumber;

    @FXML
    TextArea transactionInfoTextArea;

    private final ArrayList<Transaction> transactionList = ATM.dataStorage.getTransactionStorage().getTransactionList();

    public void setChoiceTransactionNumber(){
        choiceTransactionNumber.getItems().clear();
        for (Transaction transaction : transactionList){
            choiceTransactionNumber.getItems().add(transaction.getTransactionNumber());
        }
    }

    public void printTransactionInfo(){
        try {
            for (Transaction transaction : transactionList){
                if (transaction.getTransactionNumber() == choiceTransactionNumber.getValue()){
                    transactionInfoTextArea.setText("Transaction Type : " + transaction.getType()
                            + "\nAccount Number : " + transaction.getAccountNumber()
                            + "\nAnother Account Number : " + transaction.getAccountNumberOfOther()
                            + "\nAmount : " + transaction.getAmount()
                            + "\nAble to Undo : " + transaction.getIsAbleToUndo());
                }
            }
        } catch (Exception ex){
            transactionInfoTextArea.setText("You need to choose transaction!");
        }
    }

    public void pressConfirm(){
        try {
            ATM.bankManager.undoTransaction(choiceTransactionNumber.getValue());
            printTransactionInfo();
        } catch (NullPointerException ex){
            transactionInfoTextArea.setText("You need to choose transaction!");
        } catch (UnableToUndoTransactionException ex1){
            transactionInfoTextArea.setText("You can't undo this transaction!");
        } catch (MaxTransferOutExceededException ex2){
            transactionInfoTextArea.setText("Account doesn't have enough balance to undo!");
        } catch (CannotWithdrawFromCreditCardException ex3){
            System.out.println("bug in transaction.txt file!");
        }
    }

    public void pressBack(){
        transactionInfoTextArea.clear();
        choiceTransactionNumber.getItems().clear();
        ATM.window.setScene(ATM.scenes.managerMainMenuScene);
    }
}
