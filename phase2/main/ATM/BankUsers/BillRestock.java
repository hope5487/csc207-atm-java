package ATM.BankUsers;

import ATM.DataStorage.BillStorage;
import ATM.MainOperation.ATM;
import java.util.Map;

class BillRestock {

    private final BillStorage billStorage = ATM.dataStorage.getBillStorage();

    void restockBill(String billType, int amount){
        Map<Integer, Integer> moneyInATM = billStorage.getNumberOfBills();
        if (billType.equalsIgnoreCase("FIVEDOLLAR")) {
            moneyInATM.replace(5, moneyInATM.get(5) + amount);
        } else if (billType.equalsIgnoreCase("TENDOLLAR")) {
            moneyInATM.replace(10, moneyInATM.get(10) + amount);
        } else if (billType.equalsIgnoreCase("TWENTYDOLLAR")) {
            moneyInATM.replace(20, moneyInATM.get(20) + amount);
        } else if (billType.equalsIgnoreCase("FIFTYDOLLAR")) {
            moneyInATM.replace(50, moneyInATM.get(50) + amount);
        }
        billStorage.writeBillsFile();
    }
}
