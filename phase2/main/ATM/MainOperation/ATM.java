package ATM.MainOperation;

import java.time.LocalTime;

import ATM.DataStorage.*;
import ATM.BankUsers.*;
import ATM.GUI.Scenes;
import ATM.TimePackage.CountdownDay;
import ATM.TimePackage.CountdownSecond;
import javafx.application.Application;
import javafx.stage.Stage;

public class ATM extends Application{

    public static final DataStorage dataStorage = new DataStorage();
    public static final BankManager bankManager = new BankManager();
    public static final Scenes scenes = new Scenes();
    public static Stage window;
    public static User currentUser;

    public static void StartCountdown(){
        LocalTime currentTime = LocalTime.now();
        int hour = 23 - currentTime.getHour();
        int min = 60 - currentTime.getMinute();
        int second = hour * 3600 + min * 60;
        new CountdownDay(second);
        new CountdownSecond();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        scenes.sceneSetter();
        window = primaryStage;
        window.setTitle("ATM");
        if (dataStorage.getDateStorage().getTime().getIsEmptyDate()){ //If file is empty, need to set date first!
            window.setScene(scenes.dateScene);
        } else { window.setScene(scenes.logInScene); }
        window.show();
    }


    public static void main(String[] args){
        dataStorage.getBillStorage().readBillsFile();
        dataStorage.getDateStorage().readDateFile();
        dataStorage.getPortfolioStorage().readExchangeFile();
        dataStorage.getPortfolioStorage().readPortfolioFile();
        dataStorage.getUserStorage().readUserFile();
        dataStorage.getAccountStorage().readAccountFile();
        dataStorage.getUserStorage().writeUserFile();
        dataStorage.getAccountStorage().writeAccountFile();
        dataStorage.getItemStorage().setItemList();
        StartCountdown();
        launch(args);
    }
}
