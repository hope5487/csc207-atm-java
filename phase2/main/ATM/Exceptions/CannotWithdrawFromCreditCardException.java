package ATM.Exceptions;

public class CannotWithdrawFromCreditCardException extends Exception {
    public CannotWithdrawFromCreditCardException(){
        super("Withdraw is not allowed for Credit Card");
    }
}
