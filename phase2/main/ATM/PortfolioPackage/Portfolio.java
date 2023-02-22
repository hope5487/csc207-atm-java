package ATM.PortfolioPackage;

import ATM.Exceptions.MaxTransferOutExceededException;
import ATM.Exceptions.StockAmountExceededException;
import ATM.MainOperation.ATM;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Portfolio {

    private double balance;

    private final int portfolioNumber = ATM.dataStorage.getPortfolioStorage().getPortfolioList().size();

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPortfolioNumber() {
        return portfolioNumber;
    }

    private final HashMap<Stock, Integer> stockHaspMap = new HashMap<>();

    public HashMap<Stock, Integer> getStockHaspMap(){
        return stockHaspMap;
    }

    public void buyStock(String ticker, int amount) throws MaxTransferOutExceededException {
        Stock stock = ATM.dataStorage.getPortfolioStorage().searchStock(ticker);
        if (balance >= amount * stock.getPrice()){
            if (stockHaspMap.containsKey(stock)){
                stockHaspMap.replace(stock, stockHaspMap.get(stock) + amount);
            } else {
                stockHaspMap.put(stock, amount);
            }
            updateBalanceWhenBuyStock(amount, stock);
        } else {
            throw new MaxTransferOutExceededException();
        }
    }

    private void updateBalanceWhenBuyStock(int amount, Stock stock){
        balance -= amount * stock.getPrice();
        roundUpBalance();
        ATM.dataStorage.getPortfolioStorage().writePortfolioFile();
    }

    public void sellStock(String ticker, int amount) throws StockAmountExceededException{
        Stock stock = ATM.dataStorage.getPortfolioStorage().searchStock(ticker);
        if (stockHaspMap.containsKey(stock)){
            if (stockHaspMap.get(stock) < amount){
                throw new StockAmountExceededException();
            } else {
                stockHaspMap.replace(stock, stockHaspMap.get(stock) - amount);
                if (stockHaspMap.get(stock) == 0){
                    stockHaspMap.remove(stock);
                }
                balance += amount * stock.getPrice();
                roundUpBalance();
                ATM.dataStorage.getPortfolioStorage().writePortfolioFile();
            }
        } else {
            throw new StockAmountExceededException();
        }
    }

    private void roundUpBalance(){
        DecimalFormat df = new DecimalFormat("####0.00");
        balance = Double.parseDouble(df.format(balance));
    }
}