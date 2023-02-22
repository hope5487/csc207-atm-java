package ATM.GUI.ForManager.Controller;

import ATM.MainOperation.ATM;

public class ManagerMainMenuGUIController {

    public void pressLogOut(){
        ATM.window.setScene(ATM.scenes.logInScene);
    }

    public void pressManageAccount() { ATM.window.setScene(ATM.scenes.managerManageAccountScene); }

    public void pressUndoTransaction() { ATM.window.setScene(ATM.scenes.managerUndoTransactionScene); }

    public void pressManageUser() { ATM.window.setScene(ATM.scenes.managerManageUserScene); }

    public void pressRestockBills() { ATM.window.setScene(ATM.scenes.managerBillRestockScene); }

}
