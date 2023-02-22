package ATM.DataStorage;

import ATM.PortfolioPackage.Portfolio;
import ATM.PortfolioPackage.Stock;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PortfolioStorage {

    private final String pathNameForExchangeFile = System.getProperty("user.dir")
            + "/phase2/inputTextFiles/nasdaq100list.txt";

    private final String pathNameForPortfolioFile = System.getProperty("user.dir")
            + "/phase2/inputTextFiles/portfolio.txt";

    private final ArrayList<Portfolio> portfolioList = new ArrayList<>();

    public ArrayList<Portfolio> getPortfolioList() {
        return portfolioList;
    }

    private final ArrayList<Stock> stockList = new ArrayList<>();

    public ArrayList<Stock> getStockList() {
        return stockList;
    }

    public void readExchangeFile(){
        try {
            Scanner scanner = new Scanner(new File(pathNameForExchangeFile));
            while (scanner.hasNextLine()) {
                String[] record = scanner.nextLine().split(",");
                String ticker = record[0];
                String stockName = record[1];
                double price = Double.parseDouble(record[2]);
                Stock st = new Stock(ticker, stockName, price);
                stockList.add(st);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeExchangeFile(){
        FileWriter fw;
        try {
            fw = new FileWriter(new File(pathNameForExchangeFile));
            for (Stock stock : stockList) {
                fw.write(stock.getTicker() + "," + stock.getStockName() + "," + stock.getPrice() + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void readPortfolioFile(){
        Portfolio currentPortfolio = new Portfolio();
        try {
            Scanner scanner = new Scanner(new File(pathNameForPortfolioFile));
            while (scanner.hasNextLine()) {
                String[] record = scanner.nextLine().split(",");
                if (record[0].equals("portfolio")){
                    Portfolio portfolio = new Portfolio();
                    portfolio.setBalance(Double.parseDouble(record[1]));
                    portfolioList.add(portfolio);
                    currentPortfolio = portfolio;
                } else {
                    Stock stock = searchStock(record[1]);
                    currentPortfolio.getStockHaspMap().put(stock, Integer.parseInt(record[2]));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writePortfolioFile(){
        FileWriter fw;
        try {
            fw = new FileWriter(new File(pathNameForPortfolioFile));
            for (Portfolio portfolio : portfolioList) {
                fw.write("portfolio," + portfolio.getBalance() + "\n");
                for (Stock stock : portfolio.getStockHaspMap().keySet()) {
                    fw.write("stock," + stock.getTicker() + "," +portfolio.getStockHaspMap().get(stock) + "\n");
                }
            }
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Stock searchStock(String ticker){
        for (Stock stock : stockList){
            if (stock.getTicker().equals(ticker)){
                return stock;
            }
        }
        return null;
    }
}

