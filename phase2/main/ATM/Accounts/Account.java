package ATM.Accounts;

import ATM.Exceptions.CannotWithdrawFromCreditCardException;
import ATM.Exceptions.MaxTransferOutExceededException;
import ATM.Exceptions.NotEnoughBillsException;
import ATM.MainOperation.ATM;
import ATM.TimePackage.Time;

import java.util.ArrayList;
import java.util.Map;

public abstract class Account {

    private double balance;
    private Time dateOfCreation;
    private final int accountNumber = ATM.dataStorage.getAccountStorage().getAccountList().size();
    private final ArrayList<String> ownerNameList = new ArrayList<>();
    private Time validDate;
    private boolean isValid = true;

    Account(){
        Time time = ATM.dataStorage.getDateStorage().getTime();
        int year = time.getYear();
        int month = time.getMonth();
        int day = time.getDay();
        this.dateOfCreation = new Time(year, month, day);
        this.validDate = new Time(year + 4, month, day);
    }

    public Time getValidDate(){
        return validDate;
    }

    public void setValidDate(int year, int month, int day) {
        this.validDate = new Time(year, month, day);
    }

    public void renewValidDate(){
        Time currentTime = ATM.dataStorage.getDateStorage().getTime();
        validDate = new Time(currentTime.getYear() + 4, currentTime.getMonth(), currentTime.getDay());
    }

    public boolean getIsValid(){
        return isValid;
    }

    public void setIsValid(boolean isValid){
        this.isValid = isValid;
    }

    public void setDateOfCreation(int year, int month, int day) {
        this.dateOfCreation = new Time(year, month, day);
    }

    public void setBalance(double amount){
        this.balance = amount;
    }

    public double getBalance(){
        return this.balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public ArrayList<String> getOwnerNameList() {
        return ownerNameList;
    }

    public void addOwner(String ownerName) {
        if (!ownerNameList.contains(ownerName)){
            this.ownerNameList.add(ownerName);
        }
    }

    public void removeOwner(String ownerName) {
        if (ownerNameList.contains(ownerName)){
            this.ownerNameList.remove(ownerName);
        }
    }

    public Time getDateOfCreation() {
        return dateOfCreation;
    }

    public void deposit(int amount){
        transferIn(amount);
    }

    public void withdraw(int billType, int amount) throws MaxTransferOutExceededException, CannotWithdrawFromCreditCardException,
            NotEnoughBillsException {
        Map<Integer, Integer> bills = ATM.dataStorage.getBillStorage().getNumberOfBills();
        if (bills.get(billType) < amount / billType){
            throw new NotEnoughBillsException();
        }
        this.transferOut(amount);
        updateBillsInATM(billType, amount);
        ATM.dataStorage.getBillStorage().writeBillsFile();
        ATM.bankManager.checkBillsInATM();
    }

    private void updateBillsInATM(int billType, int amount){
        Map<Integer, Integer> bills = ATM.dataStorage.getBillStorage().getNumberOfBills();
        if (billType == 50) {
            bills.replace(50, bills.get(50) - amount/50);
        } else if (billType == 20) {
            bills.replace(20, bills.get(20) - amount/20);
        } else if (billType == 10) {
            bills.replace(10, bills.get(10) - amount/10);
        } else if (billType == 5) {
            bills.replace(5, bills.get(5) - amount/5);
        }
    }

    public abstract void transferIn(double amount);

    public abstract void transferOut(double amount) throws MaxTransferOutExceededException,
            CannotWithdrawFromCreditCardException;

    public void pay(double amount) throws MaxTransferOutExceededException, CannotWithdrawFromCreditCardException{
        transferOut(amount);
    }
}
