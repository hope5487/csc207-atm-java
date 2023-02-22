package ATM.GUI.ForUser.Controller;

import ATM.Accounts.Account;
import ATM.BankUsers.BankWorker;
import ATM.DataStorage.Transaction;
import ATM.Exceptions.MaxTransferOutExceededException;
import ATM.MainOperation.ATM;
import ATM.PortfolioPackage.Portfolio;
import ATM.PortfolioPackage.Stock;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class UserManagePortfolioGUIController {

    @FXML
    private TextArea textArea;

    @FXML
    private ComboBox<String> comboTicker;

    @FXML
    private TextField numberOfStock, amountToTransfer;

    @FXML
    private Label systemMessage;

    private final ArrayList<Account> accountList = ATM.dataStorage.getAccountStorage().getAccountList();
    private final ArrayList<Transaction> transactionList = ATM.dataStorage.getTransactionStorage().getTransactionList();
    private final ArrayList<Stock> stockList = ATM.dataStorage.getPortfolioStorage().getStockList();
    private Portfolio portfolio;

    private void setPortfolio() {
        this.portfolio = ATM.dataStorage.getPortfolioStorage().getPortfolioList().get(ATM.currentUser.getPortfolioNumber());
    }

    private void setSystemMessage(){
        systemMessage.setText("You need to choose ticker, transaction type, and amount!");
    }

    public void setComboTicker() {
        if (comboTicker.getItems().isEmpty()){
            for (Stock stock : stockList) {
                comboTicker.getItems().add(stock.getTicker());
            }
        }
    }

    public void pressTransferToDefaultAccount(){
        try {
            Account account = accountList.get(ATM.currentUser.getPrimaryAccountNumber());
            int amount = Integer.parseInt(amountToTransfer.getText());
            if (amount > portfolio.getBalance()){
                systemMessage.setText("Amount to transfer exceed your portfolio balance!");
            } else {
                account.transferIn(amount);
                portfolio.setBalance(portfolio.getBalance() - amount);
                Transaction transaction = new Transaction("transfer", -1,
                        account.getAccountNumber(), amount);
                transaction.setIsAbleToUndo(false);
                transactionList.add(transaction);
                ATM.dataStorage.getAccountStorage().writeAccountFile();
                ATM.dataStorage.getPortfolioStorage().writePortfolioFile();
                pressView();
            }
        } catch (Exception ex) {
            systemMessage.setText("You need to enter amount to transfer!");
        }
    }

    public void pressTransferFromDefaultAccount(){
        try {
            Account account = accountList.get(ATM.currentUser.getPrimaryAccountNumber());
            int amount = Integer.parseInt(amountToTransfer.getText());
            account.transferOut(amount);
            portfolio.setBalance(portfolio.getBalance() + amount);
            Transaction transaction = new Transaction("transfer",
                    account.getAccountNumber(),-1, amount);
            transaction.setIsAbleToUndo(false);
            transactionList.add(transaction);
            ATM.dataStorage.getAccountStorage().writeAccountFile();
            ATM.dataStorage.getPortfolioStorage().writePortfolioFile();
            pressView();
        } catch (MaxTransferOutExceededException maxEx){
            systemMessage.setText("Amount to transfer exceed your default account balance!");
        } catch (Exception ex){
            systemMessage.setText("You need to enter amount to transfer!");
        }
    }

    public void pressBuy() {
        setPortfolio();
        try {
            int amount = Integer.parseInt(numberOfStock.getText());
            String ticker = comboTicker.getValue();
            for (Stock stock : stockList) {
                if (stock.getTicker().equals(ticker)) {
                    portfolio.buyStock(ticker, amount);
                    break;
                }
            }
        } catch (Exception ex) {
            setSystemMessage();
        }
        pressView();
    }

    public void pressSell() {
        setPortfolio();
        try {
            int amount = Integer.parseInt(numberOfStock.getText());
            String ticker = comboTicker.getValue();
            for (Stock stock : stockList) {
                if (stock.getTicker().equals(ticker)) {
                    portfolio.sellStock(ticker, amount);
                    break;
                }
            }
        } catch (Exception ex) {
            setSystemMessage();
        }
        pressView();
    }

    public void pressView() {
        setPortfolio();
        textArea.clear();
        textArea.appendText("Current Balance : $" + portfolio.getBalance() + "\n");
        for (Stock stock : portfolio.getStockHaspMap().keySet()) {
            textArea.appendText(stock.getTicker() + " : " + portfolio.getStockHaspMap().get(stock) + "\n");
        }
    }

    //Go back to main menu
    public void pressBack(){
        numberOfStock.clear();
        textArea.clear();
        if (ATM.currentUser instanceof BankWorker){
            ATM.window.setScene(ATM.scenes.bankWorkerMenuScene);
        } else {
            ATM.window.setScene(ATM.scenes.userMainMenuScene);
        }
    }

}
