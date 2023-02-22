package ATM.Exceptions;

public class UnableToUndoTransactionException extends Exception{
    public UnableToUndoTransactionException(){
        super("Unable to undo!");
    }
}
