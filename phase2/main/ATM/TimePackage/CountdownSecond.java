package ATM.TimePackage;

import ATM.MainOperation.ATM;
import ATM.PortfolioPackage.Stock;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownSecond {

    private final Timer timer;

    public CountdownSecond() {
        timer = new Timer();
        timer.schedule(new RemindTask(), 10000);
    }


    class RemindTask extends TimerTask {
        public void run() {
            timer.cancel();
            searchStocksAndUpdate();
            new CountdownSecond();
        }
    }

    //helper methods for updateTimeOfATM(), search stocks in portfolio and update their prices.
    private void searchStocksAndUpdate(){
        for (Stock stock : ATM.dataStorage.getPortfolioStorage().getStockList()){
            stock.updatePriceRandomly();
        }
        ATM.dataStorage.getPortfolioStorage().writeExchangeFile();
    }
}

