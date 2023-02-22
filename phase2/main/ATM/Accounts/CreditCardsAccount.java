package ATM.Accounts;

import ATM.Exceptions.CannotWithdrawFromCreditCardException;
import ATM.Exceptions.MaxTransferOutExceededException;

public class CreditCardsAccount extends Account {

    public CreditCardsAccount(){
        super();
    }

    public void transferIn(double amount){
        setBalance(getBalance() - amount);
    }

    public void transferOut(double amount) throws CannotWithdrawFromCreditCardException {
        throw new CannotWithdrawFromCreditCardException();
    }

    @Override
    public void withdraw(int billType, int amount) throws CannotWithdrawFromCreditCardException {
        throw new CannotWithdrawFromCreditCardException();
    }

    @Override
    public void pay(double amount) throws MaxTransferOutExceededException{
        if (getBalance() + amount <= 1000){
            setBalance(getBalance() + amount);
        }
        else {
            throw new MaxTransferOutExceededException();
        }
    }
}
