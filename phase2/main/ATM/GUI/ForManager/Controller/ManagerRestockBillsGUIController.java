package ATM.GUI.ForManager.Controller;

import ATM.MainOperation.ATM;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ManagerRestockBillsGUIController {

    @FXML
    private TextField amountTextField;

    @FXML
    private Label systemMessage;

    public void pressBack(){
        amountTextField.clear();
        systemMessage.setText("");
        ATM.window.setScene(ATM.scenes.managerMainMenuScene);
    }

    private void setSystemMessage(){
        systemMessage.setText("You need to enter amount!");
    }

    public void RestockFiveDollar(){
        try {
            ATM.bankManager.restockBill("fivedollar", Integer.parseInt(amountTextField.getText()));
        } catch (Exception ex){
            setSystemMessage();
        }
    }

    public void RestockTenDollar(){
        try {
            ATM.bankManager.restockBill("tendollar", Integer.parseInt(amountTextField.getText()));
        } catch (Exception ex){
            setSystemMessage();
        }
    }

    public void RestockTwentyDollar(){
        try {
            ATM.bankManager.restockBill("twentydollar", Integer.parseInt(amountTextField.getText()));
        } catch (Exception ex){
            setSystemMessage();
        }
    }

    public void RestockFiftyDollar(){
        try {
            ATM.bankManager.restockBill("fiftydollar", Integer.parseInt(amountTextField.getText()));
        } catch (Exception ex){
            setSystemMessage();
        }
    }

}
