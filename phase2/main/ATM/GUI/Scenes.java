package ATM.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class Scenes {

    public Scene dateScene, logInScene, userMainMenuScene, managerMainMenuScene, userDepositScene, userWithdrawScene,
            userPayBillScene, userTransferToAccountScene, userTransferToUserScene, userStoreScene,
            userManageAccountScene, userManagePortfolioScene, userCurrencyExchangeScene, userFAQScene,
            managerManageAccountScene, managerUndoTransactionScene, bankWorkerBankOptionsScene, bankWorkerMenuScene,
            managerManageUserScene, managerBillRestockScene;

    public void sceneSetter() throws Exception{
        dateScene = sceneLoader("ForGeneral/View/DateSetterGUI.fxml");
        logInScene = sceneLoader("ForGeneral/View/LogInGUI.fxml");
        userMainMenuScene = sceneLoader("ForUser/View/UserMainMenuGUI.fxml");
        managerMainMenuScene = sceneLoader("ForManager/View/ManagerMainMenuGUI.fxml");
        userDepositScene = sceneLoader("ForUser/View/DepositMenuGUI.fxml");
        userWithdrawScene = sceneLoader("ForUser/View/WithdrawMenuGUI.fxml");
        userPayBillScene = sceneLoader("ForUser/View/PayBillMenuGUI.fxml");
        userTransferToAccountScene = sceneLoader("ForUser/View/TransferToAccountGUI.fxml");
        userTransferToUserScene = sceneLoader("ForUser/View/TransferToUserGUI.fxml");
        userManageAccountScene = sceneLoader("ForUser/View/UserManageAccountGUI.fxml");
        userManagePortfolioScene = sceneLoader("ForUser/View/UserManagePortfolioGUI.fxml");
        userCurrencyExchangeScene = sceneLoader("ForUser/View/CurrencyExchangeMenuGUI.fxml");
        userFAQScene = sceneLoader("ForUser/View/FrequentlyAskedQuestionsMenuGUI.fxml");
        managerManageAccountScene = sceneLoader("ForManager/View/ManagerManageAccountGUI.fxml");
        userStoreScene = sceneLoader("ForUser/View/StoreMenuGUI.fxml");
        managerUndoTransactionScene = sceneLoader("ForManager/View/ManagerUndoTransactionMenuGUI.fxml");
        bankWorkerBankOptionsScene = sceneLoader("ForManager/View/BankWorkerBankOptionsMenuGUI.fxml");
        bankWorkerMenuScene = sceneLoader("ForManager/View/BankWorkerMenuGUI.fxml");
        managerManageUserScene = sceneLoader("ForManager/View/ManagerManageUserGUI.fxml");
        managerBillRestockScene = sceneLoader("ForManager/View/ManagerRestockBillsGUI.fxml");
    }

    private Scene sceneLoader(String sceneName) throws Exception{
        return new Scene(FXMLLoader.load(getClass().getResource(sceneName)));
    }
}
