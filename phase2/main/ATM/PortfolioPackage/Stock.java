package ATM.PortfolioPackage;

import java.text.DecimalFormat;
/*
 * A stock.
 */

public class Stock{

    private final String ticker;
    private final String stockName;
    private double price;

    public Stock(String ticker, String stockName, double price){
        this.ticker = ticker;
        this.stockName = stockName;
        this.price = price;
    }
    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }

    public String getStockName() {
        return stockName;
    }

    public void updatePriceRandomly(){
        double p = Math.random();
        if (p < 0.1){
            price = price - price * 0.03;
        } else if (p < 0.5){
            price = price - price * 0.01;
        } else if (p < 0.9){
            price = price + price * 0.01;
        } else {
            price = price + price * 0.03;
        }
        DecimalFormat df = new DecimalFormat("####0.00");
        price = Double.parseDouble(df.format(price));
    }



}
