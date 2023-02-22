package ATM.Accounts;

import ATM.Exceptions.MaxTransferOutExceededException;

public class LineOfCreditAccount extends Account {

    public LineOfCreditAccount(){
        super();
    }

    public void transferIn(double amount){
        this.setBalance(this.getBalance() - amount);
    }

    public void transferOut(double amount) throws MaxTransferOutExceededException {
        if (getBalance() + amount <= 1000){
            setBalance(getBalance() + amount);
        }
        else {
            throw new MaxTransferOutExceededException();
        }
    }
}
