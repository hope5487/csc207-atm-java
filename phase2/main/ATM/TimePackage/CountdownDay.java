package ATM.TimePackage;

import ATM.MainOperation.ATM;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownDay {

    private final Timer timer;

    public CountdownDay(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
    }


    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Day passed!");
            timer.cancel(); //Terminate the timer thread
            ATM.dataStorage.getDateStorage().getTime().updateTimeOfATM();
            ATM.StartCountdown();
        }
    }
}
