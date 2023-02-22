package ATM.Exceptions;


public class NotEnoughBillsException extends Exception {
    public NotEnoughBillsException(){
        super("There are not enough bills for this withdraw.");
    }
}
