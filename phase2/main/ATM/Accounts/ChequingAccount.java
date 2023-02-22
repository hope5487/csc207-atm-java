package ATM.Accounts;

import ATM.DataStorage.Transaction;
import ATM.Exceptions.MaxTransferOutExceededException;
import ATM.MainOperation.ATM;

public class ChequingAccount extends Account {

    //charge $10.0 a month for account fee.
    public void chargeMonthlyFee() {
        Transaction transaction = new Transaction("monthly account fee", this.getAccountNumber(), 10.0);
        ATM.dataStorage.getTransactionStorage().getTransactionList().add(transaction);
        ATM.dataStorage.getTransactionStorage().writeTransaction();
        setBalance(getBalance() - 10.0);
        if (this.getBalance() < 100){
            this.setIsValid(false);
        }
    }

    public void transferIn(double amount){
        this.setBalance(this.getBalance() + amount);
    }

    public void transferOut(double amount) throws MaxTransferOutExceededException {
        double balance = this.getBalance();
        if (balance > 0 && balance >= amount) {
            this.setBalance(getBalance() - amount);
        } else if (balance > 0 && balance - amount >= -100) {
            this.setBalance(getBalance() - amount);
        } else {
            throw new MaxTransferOutExceededException();
        }
    }
}
