package ATM.GUI.ForManager.Controller;

import ATM.BankUsers.BankWorker;
import ATM.BankUsers.User;
import ATM.MainOperation.ATM;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class ManagerManageUserGUIController {

    @FXML
    TextField userNameTextField;

    @FXML
    private ComboBox<String> choiceUser;

    @FXML
    private ChoiceBox<String> choiceAccountType;

    @FXML
    private Label systemMessage;

    private final ArrayList<User> userList = ATM.dataStorage.getUserStorage().getUserList();

    public void pressBack(){
        userNameTextField.clear();
        choiceUser.getItems().clear();
        choiceAccountType.getItems().clear();
        systemMessage.setText("");
        if (ATM.currentUser instanceof BankWorker){
            ATM.window.setScene(ATM.scenes.bankWorkerMenuScene);
        } else {
            ATM.window.setScene(ATM.scenes.managerMainMenuScene);
        }
    }

    public void setChoiceUser(){
        choiceUser.getItems().clear();
        for (User user : userList){
            choiceUser.getItems().add(user.getUserName());
        }
    }

    public void setChoiceAccountType(){
        if (choiceAccountType.getItems().isEmpty()){
            choiceAccountType.getItems().add("Chequing Account");
            choiceAccountType.getItems().add("Saving Account");
            choiceAccountType.getItems().add("Credit Cards Account");
            choiceAccountType.getItems().add("Line of Credit Account");
            choiceAccountType.getItems().add("Student Account");
        }
    }

    public void pressCreateUser(){
        String userName = userNameTextField.getText().toLowerCase();
        if (userName.equals("") || userName.equalsIgnoreCase("BM")){
            systemMessage.setText("Not Valid User Name!");
        } else {
            boolean isValidName = true;
            for (User user : ATM.dataStorage.getUserStorage().getUserList()){
                if (user.getUserName().equals(userName)){
                    isValidName = false;
                    break;
                }
            }
            if (isValidName){
                ATM.bankManager.createUser(userName);
                systemMessage.setText("User created!");
            }
        }
    }

    public void pressCreateAccount(){
        try {
            String userName = choiceUser.getValue();
            User user = ATM.dataStorage.getUserStorage().searchUser(userName);
            ATM.bankManager.createAccount(user, choiceAccountType.getValue());
            systemMessage.setText("Account created!");
        } catch (Exception ex) {
            systemMessage.setText("You need to choose user and account type!");
        }
    }

    public void promoteUser(){
        try {
            String userName = choiceUser.getValue();
            User user = ATM.dataStorage.getUserStorage().searchUser(userName);
            if (user instanceof BankWorker){
                systemMessage.setText("This user is already bank worker!");
            } else {
                ATM.bankManager.promoteToBankWorker(user);
                systemMessage.setText("This user promoted to bank worker!");
            }
        } catch (Exception ex) {
            systemMessage.setText("You need to choose user!");
        }
    }

    public void demoteBankWorker(){
        try {
            String userName = choiceUser.getValue();
            User user = ATM.dataStorage.getUserStorage().searchUser(userName);
            if (user instanceof BankWorker){
                ATM.bankManager.demoteToUser((BankWorker) user);
                systemMessage.setText("This user demoted to user!");
            } else {
                systemMessage.setText("This user is not bank worker!");
            }
        } catch (Exception ex) {
            systemMessage.setText("You need to choose user!");
        }
    }
}
