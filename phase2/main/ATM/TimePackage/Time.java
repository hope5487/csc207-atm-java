package ATM.TimePackage;

import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;
import ATM.Accounts.SavingAccount;
import ATM.Accounts.StudentAccount;
import ATM.MainOperation.ATM;

public class Time {

    private int year;
    private int month;
    private int day;
    private boolean isEmptyDate = true;

    public Time(){
        super();
    }

    public Time(int year, int month, int day) {
        super();
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean getIsEmptyDate() {
        return isEmptyDate;
    }

    public void setIsEmptyDate(boolean isEmptyDate) {
        this.isEmptyDate = isEmptyDate;
    }

    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    //update date of ATM machine and update balances based on current Time
    public void updateTimeOfATM() {
        Time currentTime = ATM.dataStorage.getDateStorage().getTime();
        int pastMonth = currentTime.getMonth();
        currentTime.updateDate();
        searchAccountPassValidDate();
        ATM.dataStorage.getDateStorage().writeDateFile();
        if (currentTime.getMonth() != pastMonth){ //if month increases
            System.out.println("Month Passed!");
            searchSavingAccountAndUpdate();
            monthlyAccountFee();
            ATM.dataStorage.getAccountStorage().writeAccountFile();
        }
    }

    //helper methods for updateTimeOfATM(), search saving accounts and update balance.
    private void searchSavingAccountAndUpdate() {
        for (Account account : ATM.dataStorage.getAccountStorage().getAccountList()){
            if (account instanceof SavingAccount){
                ((SavingAccount) account).updateBalance();
                System.out.println("balance updated!");
            }
        }
    }

    //search account that passed valid date and make it un-valid
    private void searchAccountPassValidDate() {
        for (Account account : ATM.dataStorage.getAccountStorage().getAccountList()){
            if (account.getValidDate().equals(ATM.dataStorage.getDateStorage().getTime())){
                account.setIsValid(false);
                System.out.println(account.getAccountNumber() + " now un-valid!");
            }
        }
        ATM.dataStorage.getAccountStorage().writeAccountFile();
    }

    private void monthlyAccountFee() {
        for (Account account : ATM.dataStorage.getAccountStorage().getAccountList()){
            if (!(account instanceof StudentAccount) && account instanceof ChequingAccount){
                ((ChequingAccount)account).chargeMonthlyFee();
                System.out.println(account.getAccountNumber() + " charged account fee!");
            }
        }
    }


    //helper methods for updateTimeOfATM()
    private void updateDate(){
        day ++;
        if (month == 2){
            updateFebruary();
        }
        else if (month == 1 || month == 3 || month == 5 || month == 7 ||
                month ==8 || month == 10 || month == 12){ updateMonthWith31Days(); }
        else { updateMonthWith30Days(); }
    }

    private void updateFebruary(){
        if (year % 4 == 0){
            if (year % 100 == 0){
                if (year % 400 == 0){ updateMonthOfLeapYear(); }
                else { updateMonthOfCommonYear(); }
            }
            else { updateMonthOfLeapYear(); }
        }
        else { updateMonthOfCommonYear(); }
    }

    private void updateMonthOfLeapYear(){
        if (day > 29){
            day -= 29;
            month ++;
        }
    }

    private void updateMonthOfCommonYear(){
        if (day > 28){
            day -= 28;
            month ++;
        }
    }

    private void updateMonthWith31Days(){
        if (day > 31){
            day -= 31;
            month ++;
            if (month > 12){
                month -= 12;
                year ++;
            }
        }
    }

    private void updateMonthWith30Days(){
        if (day > 30){
            day -= 30;
            month ++;
        }
    }

    @Override
    public String toString(){
        return year + "/" + month + "/" + day;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object instanceof Time){
            return this.getYear() == ((Time) object).getYear() &&
                    this.getMonth() == ((Time) object).getMonth() &&
                    this.getDay() == ((Time) object).getDay();
        }
        return false;
    }

    @Override
    public int hashCode(){
        String stringBuilder = String.valueOf(year) + month + day;
        return Integer.parseInt(stringBuilder);
    }

}
