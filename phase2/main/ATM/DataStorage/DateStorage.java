package ATM.DataStorage;

import ATM.TimePackage.Time;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DateStorage {

    private Time time = new Time();

    public Time getTime() {
        return time;
    }

    private final String dateFilePath = System.getProperty("user.dir")
            + "/phase2/inputTextFiles/date.txt";

    //read date.txt to set time of ATM machine.
    public void readDateFile(){
        try {
            Scanner scanner = new Scanner(new File(dateFilePath));
            if (scanner.hasNextLine()){
                String[] record = scanner.nextLine().split("/");
                int year = Integer.parseInt(record[0]);
                int month = Integer.parseInt(record[1]);
                int day = Integer.parseInt(record[2]);
                time = new Time(year, month, day);
                time.setIsEmptyDate(false);
                System.out.println("today is " + month + "/" + day);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //write date.txt based on current date of ATM machine.
    public void writeDateFile(){
        FileWriter fw;
        try {
            fw = new FileWriter(new File(dateFilePath));
            fw.write(time.toString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
