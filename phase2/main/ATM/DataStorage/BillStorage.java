package ATM.DataStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BillStorage {

    private final Map<Integer, Integer> numberOfBills = new HashMap<>(4);

    public Map<Integer, Integer> getNumberOfBills() {
        return numberOfBills;
    }

    private final String billFilePath = System.getProperty("user.dir")
            + "/phase2/inputTextFiles/bills.txt";

    public void readBillsFile(){
        try {
            Scanner scanner = new Scanner(new File(billFilePath));
            String[] record = scanner.nextLine().split(",");
            int fiveDollar = Integer.parseInt(record[0]);
            int tenDollar = Integer.parseInt(record[1]);
            int twentyDollar = Integer.parseInt(record[2]);
            int fiftyDollar = Integer.parseInt(record[3]);
            numberOfBills.put(5, fiveDollar);
            numberOfBills.put(10, tenDollar);
            numberOfBills.put(20, twentyDollar);
            numberOfBills.put(50, fiftyDollar);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeBillsFile(){
        FileWriter fw;
        try {
            fw = new FileWriter(new File(billFilePath));
            fw.write(numberOfBills.get(5) + "," + numberOfBills.get(10) + ","
                    + numberOfBills.get(20) + ","  + numberOfBills.get(50));
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
