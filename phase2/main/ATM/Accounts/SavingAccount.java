package ATM.Accounts;

import ATM.Exceptions.MaxTransferOutExceededException;

public class SavingAccount extends Account {

    public void transferIn(double amount){
        setBalance(getBalance() + amount);
    }

    public void transferOut(double amount) throws MaxTransferOutExceededException {
        double balance = this.getBalance();
        if (balance >= amount) {
            this.setBalance(getBalance() - amount);
        } else {
            throw new MaxTransferOutExceededException();
        }
    }

    public void updateBalance(){
        double balance = this.getBalance();
        this.setBalance(balance + balance * 0.1);
    }
}
