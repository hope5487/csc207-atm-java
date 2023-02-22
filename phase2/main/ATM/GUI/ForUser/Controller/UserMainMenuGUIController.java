package ATM.GUI.ForUser.Controller;

import ATM.MainOperation.ATM;

public class UserMainMenuGUIController {

    public void pressLogOut(){
        ATM.window.setScene(ATM.scenes.logInScene);
    }

    public void pressDeposit(){
        ATM.window.setScene(ATM.scenes.userDepositScene);
    }

    public void pressWithdraw(){
        ATM.window.setScene(ATM.scenes.userWithdrawScene);
    }

    public void pressTransferToAccount() { ATM.window.setScene(ATM.scenes.userTransferToAccountScene); }

    public void pressTransferToUser() { ATM.window.setScene(ATM.scenes.userTransferToUserScene); }

    public void pressPayBill() { ATM.window.setScene(ATM.scenes.userPayBillScene); }

    public void pressManageAccount() { ATM.window.setScene(ATM.scenes.userManageAccountScene); }

    public void pressManagePortfolio() { ATM.window.setScene(ATM.scenes.userManagePortfolioScene); }

    public void pressCurrencyExchange() {ATM.window.setScene(ATM.scenes.userCurrencyExchangeScene);}

    public void pressFrequentlyAskedQuestions() {ATM.window.setScene(ATM.scenes.userFAQScene);}

    public void pressStore() {ATM.window.setScene(ATM.scenes.userStoreScene);}

    public void pressBankOptions() {ATM.window.setScene(ATM.scenes.bankWorkerBankOptionsScene);}

}
