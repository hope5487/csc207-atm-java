package ATM.GUI.ForGeneral.Controller;

import ATM.BankUsers.BankWorker;
import ATM.MainOperation.ATM;
import ATM.BankUsers.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class LogInGUIController {

    @FXML
    private Label logInScreenLabel;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField userIdTextField;

    private final ArrayList<User> userList = ATM.dataStorage.getUserStorage().getUserList();

    //when user press login button, this method will be called.
    public void pressLogIn(){
        String userId = userIdTextField.getText();
        String password = passwordTextField.getText();
        userAuthentication(userId, password);
    }

    //helper method for pressLogIn()
    private void userAuthentication(String username, String password) {
        if (userList.isEmpty()) {
            ATM.currentUser = null;
            ATM.window.setScene(ATM.scenes.managerMainMenuScene); //change scene to main menu for manager
        } else {
            if (username.equalsIgnoreCase("BM") && password.equalsIgnoreCase("BM")) {
                ATM.currentUser = null;
                ATM.window.setScene(ATM.scenes.managerMainMenuScene); //change scene to main menu for manager
            } else {
                boolean loggedIn = searchUser(username, password);
                if (!loggedIn) { logInScreenLabel.setText("LogIn Failed"); } //change label text to show login has failed
                else { logInScreenLabel.setText("Enter Your Credentials");}
            }
        }
    }

    //helper method to search user and return whether user or manager logged in or not.
    private boolean searchUser(String username, String password){
        boolean loggedIn = false;
        for (User user : userList) {
            if (username.equals(user.getUserName()) && password.equals(user.getPassword())) {
                loggedIn = true;
                ATM.currentUser = user;
                userIdTextField.clear();
                passwordTextField.clear();
                if (user instanceof BankWorker){
                    ATM.window.setScene(ATM.scenes.bankWorkerMenuScene); //change scene to main menu for user
                } else {
                    ATM.window.setScene(ATM.scenes.userMainMenuScene); //change scene to main menu for user
                }
                break;
            }
        }
        return loggedIn;
    }
}
